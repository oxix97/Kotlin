package SOPT

fun prob1() = "안녕하세요 저는 안드로이드 파트 YB 이종찬 입니다. !"
fun prob2(a: Int, b: Int, c: Int): Int {
    var max = 0
    if (a > b) {
        if (a > c) {
            max = a
        } else if (c > b) {
            max = c
        }
    } else {
        if (b > c) {
            max = b
        } else {
            max = c
        }
    }
    return max
}

fun prob3(): Int {
    var answer = 0
    for (i in 1..10) {
        answer += i
    }
    return answer
}

fun main() {
    println(prob1())
    println(prob2(4, 2, 3))
    println(prob3())

}