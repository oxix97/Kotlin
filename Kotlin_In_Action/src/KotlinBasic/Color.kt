package KotlinBasic

import java.lang.Exception

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

    println(mix(Color.RED,Color.BLUE))
    println(mix(Color.INDIGO,Color.BLUE))

}

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED,Color.BLUE) -> Color.GREEN
        setOf(Color.RED,Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW,Color.BLUE) -> Color.INDIGO

        else -> throw Exception("Dirty Color")
    }


fun getColor(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN, Color.BLUE -> "neutral"
    Color.INDIGO, Color.VIOLET -> "Cool"
}
