# 함수형 프로그래밍

---

### 함수란?

함수는 여러 값을 입력받아 기능을 수행하고 결과값을 반환하는 코드의 모음이다.

---

### 함수형 프로그래밍이란??

순수 함수를 작성하여 프로그램의 부작용을 줄이는 프로그래밍 기법을 의미한다. 함수형 프로그래밍에서는 람다식과 고차 함수를 사용한다.

---

#### 함수형 프로그래밍의 정의와 특징

- 순수 함수를 사용해야한다.
- 람다식을 사용할 수 있다.
- 고차 함수를 사용할 수 있다.

---

#### 순수함수

만일 어떤 함수가 같은 인자에 대하여 항상 같은 결과를 반환하는 경우 부작용이 없는 함수라고 말한다. 그리고 부작용이 없는 함수가 함수 외부의 어떤 상태도 바꾸지 않는다면 순수함수라고 부르게 된다. 이러한 순수함수는
스레드에 사용해도 안전하고 코드를 테스트하기 쉽다는 장점이 있다.

#### 순수 함수의 조건

- 같은 인자에 대하여 항상 같은 값을 반환한다.
- 함수 외부의 어떤 상태도 바꾸지 않는다.

---

#### 람다식

람다식은 람다 대수에서 유래한 것으로 다음과 같은 형태를 나타낸다.

~~~
{x , y -> x + y}
~~~

함수의 이름이 없고 화살표가 사용되었는데 수학에서 말하는 람다 대수는 이름이 없는 함수로 2개이상의 입력을 1개의 출력으로 단순화한다는 개념이다.

---

#### 일급 객체

함수형 프로그래밍에서는 함수를 일급 객체로 생각한다.

#### 특징

- 일급 객체는 함수의 인자로 전달할 수 있다.
- 일급 객체는 함수의 반환값에 사용할 수 있다.
- 일급 객체는 변수에 담을 수 있다.

함수가 일급 객체면 일급 함수라고 부르며 일급함수에 이름이 없는 경우 람다식 함수, 람다식이라고 부를 수 있다. 람다식은 일급 객체의 특징을 가진 이름없는 함수이다.

---

#### 고차 함수

다른 함수를 인자로 사용하거나 함수를 결과값으로 반환하는 함수를 의미한다. 일급객체, 일급함수를 서로 주고받을 수 있는 함수가 고차함수이다.

#### 반환값에 일반 함수 적용

~~~kotlin
fun main() {
    println(hightFuc({ x, y -> x + y }, 10, 20))
}
fun highFunc(sum: (Int, Int) -> Int, a: Int, b: Int): Int = sum(a, b)
~~~

#### 변수에 할당하는 람다식 함수 작성하기

~~~kotlin
fun main() {
    var result: Int
    val multi = { x: Int, y: Int -> x * y }
    result = multi(10, 20)
    println(result)
}
~~~

#### 결과

> 200

---

#### 매개변수에 람다식 함수를 이용한 고차 함수

~~~kotlin
fun main() {
    var result: Int
    result = highOrder({ x, y -> x + y }, 10, 20)
    println(result)
}
fun highOrder(sum: (Int, Int) -> Int, a: Int, b: Int): Int {
    return sum(a, b)
}
~~~

#### 실행결과

> 30

---

#### 인자와 반환값이 없는 람다식 함수

~~~kotlin
fun main() {
    val out: () -> Unit = { println("hello world") }
    out()
    val new = out
    new()
}
~~~

#### 실행결과

> hello world
> hello world

---

### 람다식과 고차함수 호출하기

함수의 내용을 할당하거나 인자, 반환값을 자유롭게 넘기려면 호출 방법을 이해해야 한다. 기본형 변수로 할당된 값은 스택에 있고 다른 함수에 인자로 전달하는 경우 해당 값이 복사되어 전달된다. JVM에서 실행되는
자바나 코틀린은 함수를 호출할 경우 Call by Value이다. 코틀린은 람다식을 사용할 경우 확장된 호출 방법을 사용할 수 있다.

- 값에 의한 호출
- 이름에 의한 호출
- 다른 함수의 참조에 의한 일반 함수 호출

---

#### 값에 의한 호출

~~~kotlin
fun main() {
    val result = callByValue(lambda())
    println(result)
}
fun callByValue(b: Boolean): Boolean {
    println("call by value func")
    return b
}
val lambda: () -> Boolean = {
    println("lambda func")
    true
}
~~~

#### 실행결과

> lambda function <br>
> call by value func<br>
> true

#### 이름에 의한 람다식 호출

~~~kotlin
fun main() {
    val result = callByName(otherLambda)
    println(result)
}
fun callByName(b: () -> Boolean): Boolean {
    println("call by name func")
    return b()
}
val otherLambda: () -> Boolean = {
    println("otherLambda func")
    true
}
~~~

#### 실행결과

> call by name func <br>
> otherLambda func <br>
> true