package KotlinBasic

fun main() {
    /*for (i in 10..15) {
        println("$i : "+fizzBuzz(i))
    }*/

    for (i in 21 downTo 10 step 2) {
        println(fizzBuzz(i))
    }
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "Si"
}