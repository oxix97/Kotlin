package Property

class LazyTest {
    init {
        println("init block")
    }

    val subject by lazy {
        println("lazy init")
        "Kotlin Programming"
    }

    fun flow() {
        println("not init")
        println("subject one : $subject")
        println("subject two : $subject")
    }
}

fun main() {
    val test = LazyTest()
    test.flow()
}