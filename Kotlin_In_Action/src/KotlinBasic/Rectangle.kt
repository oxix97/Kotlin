package KotlinBasic

class Rectangle(val width: Int, val height: Int) {
    val isSquare: Boolean
        get() {
            return width==height
        }
}

fun main() {
    val Rectangle = Rectangle(45,45)
    println(Rectangle.isSquare)
}