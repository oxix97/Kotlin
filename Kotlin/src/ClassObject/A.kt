package ClassObject

class A(var name: String, var old: Int) {}

fun main() {
    val name = A("name", 3)
    println("name : ${name.name}")
    println("old : ${name.old}")
}
