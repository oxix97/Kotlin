package KotlinBasic

fun main() {
    cast()
}

fun cast() {
    val x : Any
    x = "Hello"

    if(x is String)
        println(x.length)
    else
        print("$x")
}

fun smartCast() {
    var test : Number = 12.2
    println("$test")

    test = 12
    println("$test")

    test = 120L
    println("$test")

    test += 12.5f
    println("$test")
}