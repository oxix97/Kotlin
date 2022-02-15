package com.example.myapplication.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

suspend fun async1(): String {
    delay(1000L)
    println("one")
    return "work1"
}

suspend fun async2(): String {
    delay(1000L)
    println("two")
    return "work2"
}

private fun worksInSerial() {
    val one = GlobalScope.async {
        async1()
    }
    val two = GlobalScope.async {
        async2()
    }

    GlobalScope.launch {
        val combined = one.await() + "_" + two.await()
        println("Kotlin combined : $combined")
    }
}

fun main() {
    worksInSerial()
    readLine()
}