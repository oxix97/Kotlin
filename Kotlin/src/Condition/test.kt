package Condition

fun main() {
    for(i in 1..5) print(i) //상행 반복
    println()
    for(i in 5 downTo 1) print(i)//하행 반복
    println()
    for(i in 1..5 step 2) print(i) //상행 반복 2씩 증가
    println()
    for(i in 5 downTo 1 step 2) print(i) //하행 반복 2씩 감소
}

class MyClass {

}

fun case(obj: Any) {
    when (obj) {
        1 -> println("Int : $obj")
        "Hello" -> println("String : $obj")
        is Long -> println("Long : $obj")
        !is String -> println("Not String")
    }
}