# 코틀린 표준 함수

코틀린 표준 함수를 이용하면 코드를 더 단순화하고 읽기 좋게 만들어 준다. 표준 함수는 람다식과 고차 함수를 이용해 선언되어 있다.

---

### 람다식과 고차함수

#### 람다식

람다식은 항상 중괄호로 묶여 있으며 중괄호 안에 매개변수는 화살표 왼쪽에 배치되며 오른쪽에는 그에 따른 식을 구성한다.

~~~kotlin
val 변수 이름 : 자료형 선언 = {매개변수[,...] -> 람다식 본문}
~~~

~~~kotlin
val sum : (Int,Int) -> Int = { x,y -> x + y }
val mul = { x : Int, y : Int -> x * y }
~~~

매개 변수가 1개인 경우에는 매개 변수를 생략하고 it으로 표기할 수 있으며, 만일 추론된 반환 자료형이 Unit이 아닌 경우 본문의 마지막 표현식이 반환값으로 처리된다. 특정 라벨을 지정해 반환할 수도 있다.

---

### 고차함수

고차 함수는 함수의 매개변수로 함수를 받거나 함수 자체를 반환할 수 있는 함수이다.

~~~kotlin
fun inc(x : Int) : Int{
  return x + 1
}
fun high(name : String, body : (Int) -> Int) : Int {
  println("name : $name")
  val x = 0
  return body(x)
}
~~~

~~~kotlin
val result1 = high("Lee", { x -> inc(x + 3) })
val result2 = high("Lee") { inc(it + 3) }
val result3 = high("Lee", ::inc)
val result4 = high("Lee") { x -> x + 3 }
val result5 = high("Lee") { it + 3 }
~~~

람다식이 하나인 경우 소괄호 바깥으로 빼낼 수 있으며 기본값을 사용하는 경우에는 ::함수 이름 형태로 사용할 수 있따. 또한 람다식을 매개변수로도 넣을 수 있다.

---

### 클로저

클로저란 람다식으로 표현된 내부 함수에서 외부 범위에 선언된 변수에 접근할 수 있는 개념을 의미한다. 기본적으로 함수 안에 정의된 변수는 지역 변수로 스택에 저장되어 있다가 ㅎ마수가 끝나면 같이 사라진다. 클로저의 경우 포획한 변수는 참조가 유지되어 함수가 종료되어도 사라지지 않고 함수의 변수의 접근하거나 수정할 수 있게 해준다.

#### 클로저의 조건

- final 변수를 포획한 경우 변수 값을 람다식과 함께 저장한다.
- final이 아닌 변수를 포획한 경우 변수를 특정 wrapper로 감싸 나중에 변경하거나 읽을 수있게 한다. 이때 wrapper에 대한 참조를 람다식과 함께 저장한다.

~~~kotlin
val calc = Calc()
var result = 0 //외부 변수
Calc.addNum(2,3){x,y -> result = x+y}
println(result)  // 값을 유지하며 출력
~~~

---

## 코틀린 표준 라이브러리

코틀린 표준함수에는 let, apply, with, also, run등 여러 가지 표준함수를 제공한다. 표준 함수를 통해 기존의 복잡한 코드를 단순화 하고 효율적으로 만들 수 있다.

#### 확장 함수의 람다식 접근 방법

| 함수 이름    | 람다식의 접근 방법 | 변환 방법       |
| ------------ | ------------------ | --------------- |
| T.let        | it                 | block 결과      |
| T.also       | it                 | T. caller(it)   |
| T.apply      | this               | T. caller(this) |
| T.run or run | this               | block 결과      |
| with         | this               | Unit            |

---

### let

let() 함수는 함수를 호출하는 객체 T를 이어지는 block의 인자로 넘기고 block의 결과 값 R을 반환한다.

~~~kotlin
public inline fun <T, R> T.let(block : (T) -> R) : R {... return block(this)}
~~~

let() 함수는 제네릭의 확장 함수 형태이므로 어디든 적용 가능하다.

#### null 가능성 있는 객체에서 let() 함수 사용

~~~kotlin
obj?.let{ // obj가 null 이 아닌 경우 작업 수행
  Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
}
~~~

~~~kotlin
//if-else 사용
val a = if(null != firstName) 1 else 2

//let사용
val b = firstName?.let { 1 } ?: 2
~~~

---

### also

also 함수는 함수를 호출하는 객체 T를 이어지는 block에 전달하고 객체 T 자체를 반환한다. also와 let은 역할이 동일해 보이지만 다르다. let은 마지막으로 수행된 코드 블록의 결과를 반환하고 also 함수는 블록 안의 코드 수행 결과와 상관없이 T인 객체 this 를 반환하게 된다.

~~~kotlin
var m = 1
m = m.also {it + 3}
println(m) // 해당 값은 1이다.
~~~

연산 결과인 4가 할당되는 것이 아닌 it 원래의 값 1이 다시 m에 할당된다.

#### let, also 비교하기

~~~kotlin
fun main(){
  data class Person(var name : String, var skills : String)
  var person = Person("a","Kotlin")
  val p1 = person.let{
    it.skills = "Android"
    "success"
  }
  println(person)
  println("p1 : $p1")

  val p2 = person.also{
    it.skills = "Java"
    "success"
  }
	println(person)
  println("p2 : $p2")
}
~~~

#### 실행결과

> Person(name=a, skills=Android)
>
> p1: success
>
> Person(name=a, skills=Java)
>
> p2: Person(name=a, skills=Java)

let함수는 마지막 표현식인 success를 반환하여 p1에 할당하고 also는 람다식이 본문을 처리하지만 마지막 표현식이 아닌 person 객체 자신에 할당한다. 따라서 p2는 Person의 객체 person을 반환하고 새로운 객체 p2가 할당되어 만들어진다.

---

### apply

apply 함수는 also 함수와 마찬가지로 호출하는 객체 T를 이어지는 block으로 전달하고 객체 자체인 this를 반환한다. apply함수는 특정 객체를 생성하면서 함께 호출해야 하는 초기화 코드가 있는 경우 사용할 수 있다. apply함수와 also 함수의 다른 점은 T.()와 같은 표현에서 람다식이 확장 함수로 처리된다는 것이다.

#### 레이아웃을 초기화할 때 apply() 함수 활용하기

~~~kotlin
val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
param.gravity = Gravity.CENTER_HORIZONTAL
param.weight = 1f
param.topMargin = 100
param.bottomMargin = 100
~~~

#### apply 적용

~~~kotlin
val param = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT).apply{
  	gravity = Gravity.CENTER_HORIZONTAL
		weight = 1f
		topMargin = 100
		bottomMargin = 100
}
~~~

---

### run

run 함수는 인자가 없는 익명 함수처럼 동작하는 형태와 객체에서 호출하는 형태, 2가지로 사용할 수 있다. 객체 없이 run 함수를 사용하면 인자 없는 익명 함수처럼 사용할 수 있다.

#### apply, run 비교

~~~kotlin
fun main(){
  data class Person(var name : String, var skills : String)
  var person = Person("a","Kotlin")
  val p1 = person.apply{
    this.name = "apply"
    this.skills = "Java"
    "success"
  }
  println(person)
  println("p1 : $p1")

  val p2 = person.run{
    this.name = "run"
    it.skills = "C#"
    "success"
  }
	println(person)
  println("p2 : $p2")
}
~~~

#### 실행결과

> Person(name=apply, skills : Java)
>
> p1: Person(name=apply, skills : Java)
>
> Person(name=run, skills : C#)
>
> p2: success

run, apply 차이점을 보면 run 함수도 해당 객체를 this로 받아 변경할 수 있지만 apply 함수는 this에 해당하는 객체를 반환한 반면 run 함수는 마지막 표현식을 반환했음을 알수 있다.

---

### with

with함수는 인자로 받는 객체를 이어지는 block의 receiver로 전달하며 결괏값을 반환한다. with 함수는 run과 기능이 거의 동일한데 run 함수의 경우 receiver가 없지만 with의 경우는 receiver로 전달할 객체를 처리하므로 객체의 위치가 달라진다. 물론 null이 아닌 경우가 확실하다면 with함수만 사용해도 된다.

~~~kotlin
supportActionBar?.let{
  with(it){
    setDisplayHomeAsupEnabled(true)
    setHomeAsUpIndicator(R.drawable.icon)
  }
}
~~~

let함수로 null 검사를 하고 넘겨진 객체를 with 함수에 의해 it으로 받아 처리하고 있다. run 함수에서는 this를 받아 생략할 수 있듯이 with 함수의 본문에서 it으로 받았다. 사실 let, with함수의 포현을 병합하면 run 함수로 다음과 같이 할 수 있다.

~~~kotlin
supportActionBar?.run{
  setDisplayHomeAsUpEnabled(true)
  setHomeAsUpIndicator(R.drawable.icon)
}
~~~

---