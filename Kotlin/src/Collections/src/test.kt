import kotlin.time.Duration.Companion.seconds

class test {

}

fun main() {
    val result1 = high("Lee", { x -> inc(x + 3) })
    val result2 = high("Lee") { inc(it + 3) }
    val result3 = high("Lee", ::inc)
    val result4 = high("Lee") { x -> x + 3 }
    val result5 = high("Lee") { it + 3 }
    val n = null
    val b = n?.let { 1 } ?: 2

}

fun inc(x: Int): Int {
    return x + 1
}

fun high(name: String, body: (Int) -> Int): Int {
    println("name : $name")
    val x = 0
    return body(x)
}