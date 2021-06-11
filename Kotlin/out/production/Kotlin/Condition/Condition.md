# 프로그램 흐름 제어

- 조건문
- 반복문
- 예외

---

### 조건문

주어진 조건에 따라 다른 결과를 반환하는 코드를 의미한다. 코틀린에서는 제어문들을 간략하게 사용할 수 있는 표현이 많아 간략화된 표현을 쓰도록 노력해야 한다.

#### if-else

~~~kotlin
var max: Int

if (a > b) max = a
else max = b
~~~

기존에 이렇게 사용하던 코드를 좀 더 간략하면 한줄로 표현 가능하다.

~~~kotlin
var max = if (a > b) a else b
~~~

#### 블록을 사용하여 표현식이 길어지는 경우

~~~kotlin
fun main() {
    val a = 12
    val b = 2

    val max = if (a > b) {
        println("select A")
        a //max에 a 할당
    } else {
        println("select B")
        b//max에 b 할당
    }
    println(max)
}
~~~

위 코드에서 a,b를 비교 한 이후 선택된 변수를 println 으로 출력한다.

---

#### 연산자 in

~~~kotlin
fun main() {
    val score = 75.0
    val grade = 'F'

    if (score >= 90.0) {
        grade = 'A'
    } else if (score in 80.0..89.9) {
        grade = 'B'
    } else if (score in 70.0..79.9) {
        grade = 'C'
    }
}
~~~

기존에 사용하던 >=, && 연산자를 합쳐놓은 역할이다. 코드가 더 간략하게 바뀌었고 읽기도 더 좋아졌다. 하지만 이러한 조건이 많아지면 when 을 사용하는 것이 좋다.

#### when

자바에서 switch 문의 역할을 하고 있으며 kotlin에서는 보다 간략하게 나타낸다.

~~~kotlin
when (x) {
    1 -> println('x==1')
    2 -> println('x==2')
    else -> println('else')
}
when (x) {
    0, 1 -> println("0,1")
    else -> println('else')
}
~~~

when 블록안에 화살표기준으로 왼쪽에는 일치하는 값, 표현식, 범위로 조건을 나타내며 오른쪽에는 수행할 문장을 사용하며 {} (블록) 사용이 가능하다.

#### when문으로 다양한 조건 처리

- 함수의 반환값 사용

~~~kotlin
when (x) {
    parseInt(s) -> println('일치')
    else -> println('불일치')
}
~~~

x의 값과 parseInt(s)의 반환값이 일치하면 println 문장을 수행한다.

그 밖에 in 연산자, is 키워드를 사용할 수 있다. 또한 인자가 없어도 when 문을 사용할 수있다.

~~~kotlin
fun main() {
    val score = 85
    val grade = 'F'
    when {
        score >= 90 -> grade = 'A'
        score in 80..89 -> grade = 'B'
        score in 70..79 -> grade = 'C'
        else -> 'F'
    }
}
~~~

반대로 다양한 자료형의 인자도 받아 처리할 수 있다.

~~~kotlin
fun main() {
    case(1)
    case('Hello')
    case(System.currentTimeMillis())
    case(MyClass())
}
fun case(obj: Any) {
    when (obj) {
        1 -> println("Int : $obj")
        "Hello" -> println("String : $obj")
        is Long -> println("Long : $obj")
        !is String -> println("Not String")
    }
}
~~~

#### 결과

> Int : 1 <br>
> String : Hello <br>
> Long : 1623401397258 <br>
> Not String

---

### 반복문

자바의 반복문과 대부분 똑같다. 그렇지만 코틀린에서는 조금 더 간략하게 나타낸다.

#### for문

자바의 for문과 다른점은 코틀린에서는 세미콜론을 사용하지 않는다는 점이다.

~~~kotlin
for (x in 1..5) {
    println(x)
}
~~~

변수 x를 선언한 다음 1부터 5까지 범위를 지정해 변수에 할당하며, 본문을 반복할 때마다 x가 증가한다.

---

~~~kotlin
var sum = 0
for (x in 1..10) sum += x
~~~
조금 더 간단하게 나타내면 이렇게 나타낼 수 있다.

#### 다양한 반복 방법

~~~kotlin
for(i in 1..5) print(i) //상행 반복
for(i in 5 downTo 1) print(i)//하행 반복
for(i in 1..5 step 2) print(i) //상행 반복 2씩 증가
for(i in 5 downTo 1 step 2) print(i) //하행 반복 2씩 감소
~~~