package com.example.myapplication.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun work1(): String {
    delay(1000L)
    println("111")
    return "work1"
}

suspend fun work2(): String {
    delay(1000L)
    println("2222")
    return "work2"
}

private fun worksInSerial() {
    GlobalScope.launch {
        val one = work1()
        val two = work2()
        println("kotlin one : $one")
        println("kotlin two : $two")
    }
}

fun main() {
    worksInSerial()
    readLine()
}