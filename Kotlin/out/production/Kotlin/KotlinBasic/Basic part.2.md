---
### 스마트 캐스트

스마트 캐스트란 컴파일러가 자동으로 형 변환을 해주는 기능이다. 대표적으로 자료형 Number가 있다.

#### 스마트 캐스트 Ex
~~~kotlin
fun main() {
    var test : Number = 12.2
    println("$test")

    test = 12
    println("$test")

    test = 120L
    println("$test")

    test += 12.5f
    println("$test")
}
~~~

#### 실행결과

>12.2 <br>
12<br>
120<br>
132.5

Float, Int, Long, Float 로 스마트 캐스트 된 것을 알 수 있다.

---
### is

변수의 자료형을 알아내는 방법은 is 라는 키워드를 사용하면 되는데 자바에서는 instanceof 가 같은 역할을 한다.<br>
왼쪽 항의 변수가 오른쪽 항의 자료형과 같으면 true, 다르면 false 를 반환하게 된다. <br>
또한 스마트 캐스트는 is 로 변수에 든 값의 타입을 검사한 다음에 그 값이 바뀔 수 없는 경우에만 작동한다.
예를 들면 클래스의 프로퍼티에 대해 스마트 캐스트를 사용한다면 해당 프로퍼티는 반드시 val 이여야 하며 컷텀 접근자를 사용한 것이여도 안된다.<br>
원하는 타입으로 명시적 타입 캐스팅하려면 as 키워드를 사용하면 된다.

---
### while & for

코틀린에서 while 은 자바와 동일하다. for 는 자바의 for-each 루프에 해당하는 형태만 존재하며 컬렉션에 대한 이터레이션에 형태를 취한다.

초기값, 증가 값, 최종 값을 이용한 루프 대신 코틀린에서는 범위를 사용하는데 기본적으로 두 값으로 이뤄진 구간이다. 
그 두 값은 정수 등의 숫자 타입의 값이며, .. 연산자로 시작과 끝을 연결해서 범위를 만든다.

코틀린의 범위는 양끝을 포함한다. 1..100  -->  <= 100

#### <div id = "fizzbuzz"> FizzBuzz </div>
~~~kotlin
fun main() {
    for (i in 10..15) {
        println("$i : "+fizzBuzz(i))
    }
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "Si"
}
~~~

#### 실행결과

> 10 : Buzz <br>
11 : Si <br>
12 : Fizz <br>
13 : Si <br>
14 : Si <br>
15 : FizzBuzz

위의 [코드](#fizzbuzz) 에 추가해보자.
~~~kotlin
for (i in 21 downTo 10 step 2) {
        println(fizzBuzz(i))
    }
~~~

#### 실행결과

> Fizz <br>
Si <br>
Si <br>
FizzBuzz <br>
Si <br>
Si

down to 를 사용하여 역방향으로 반복문을 활용할 수 있으며 step 을 통해 증가 값이 2로 바뀌는 것을 알 수 있다.

