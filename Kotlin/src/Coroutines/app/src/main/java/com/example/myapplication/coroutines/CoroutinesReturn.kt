package com.example.myapplication.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val job = GlobalScope.launch {
        delay(1000L)
        println("word!!")
    }
    println("Hello")
    println("job.isActive: ${job.isActive}, completed: ${job.isCompleted}")
    Thread.sleep(2000L)
    println("job.isActive: ${job.isActive}, completed: ${job.isCompleted}")
}