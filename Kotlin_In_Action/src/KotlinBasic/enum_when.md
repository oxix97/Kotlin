## Kotlin in Action

---
### enum

코틀린에서 enum 은 자바 선언보다 더 많은 키워드를 써야하며 자바와 다르게 enum class 를 사용한다. <br>
class와는 다르게 enum은 소프트 키워드라서 키워드와는 다르게 다른 곳에 이름으로 사용할 수 있다.

~~~kotlin
enum class Color (val r: Int, val g : Int, val b : Int){
    RED(255,0,0),
    ORANGE(255,165,0),
    YELLOW(255,255,0),
    GREEN(0,255,0),
    BLUE(0,0,255),
    INDIGO(75,0,130),
    VIOLET(238,130,238);

    fun rgb() = (r * 256 + g) * 256 + b

}

fun main() {
    println(Color.BLUE.rgb())
}
~~~

#### 실행결과

> 255

enum에서도 생성자와 프로퍼티를 선언하며 해당하는 상수에 프로퍼티 값을 지정해야만 한다.
특이한 점은 enum 클래스 안에 메소드를 정의하는 경우 반드시 enum 상수 목록과 메소드 사이에 세미콜론을 넣어야한다.

---
### when

쉽게 말하면 자바의 switch 문과 같은 구성요소를 가지고 있다.
다른점은 자바와 달리 각 분기의 끝에 break 를 넣지 않아도 매치되는 분기를 찾으면 실행한다. <br>
한 분기 안에서 여러 값을 매치 패턴으로 사용하려면 ,(콤마)를 사용하여 값 사이를 분리하면 된다.

~~~kotlin
fun getColor(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN, Color.BLUE -> "neutral"
    Color.INDIGO, Color.VIOLET -> "Cool"
}

fun main() {
    println(getColor(Color.ORANGE))
    println(getColor(Color.GREEN))
    println(getColor(Color.VIOLET))
}
~~~

#### 실행결과

> warm <br>
neutral <br>
Cool

when은 switch 달리 분기 조건의 임의의 객체를 허용한다. 

#### <div id = "mix"> 예시 </div>

~~~kotlin
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED,Color.BLUE) -> Color.GREEN
        setOf(Color.RED,Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW,Color.BLUE) -> Color.INDIGO

        else -> throw Exception("Dirty Color")
    }
~~~

#### 실행결과

> GREEN <br>
<span style = "color : RED"> Exception in thread "main" java.lang.Exception: Dirty Color </span>

c1,c2 가 혼합한 결과를 보여줄 수 있으며 이를 구현하기 위해 (Set)집합 비교를 사용한다. <br>
when 은 인자 값과 매치하는 조건을 찾을 때까지 각 분기를 검사하며 조건에 없는 경우 
else -> 로 분기의 문장을 실행한다. 
when의 분기 조건 부분에 식을 넣을 수 있다는 점이 코드를 더 간결하게 작성할 수 있다.

---