package ClassObject


fun main() {
    val set = mutableSetOf<String>()
    val won = 50000
    val mush = cal(50000,2700)
    val mp = cal(54000,3000)

    println(mush.asList())
    println(mp.asList())
}

fun cal(pay: Int, operation: Int): Array<Int> {
    var answer = pay
    var i = 0
    while (answer > operation) {
        answer -= operation
        i++
    }

    return arrayOf(i,answer)
}
