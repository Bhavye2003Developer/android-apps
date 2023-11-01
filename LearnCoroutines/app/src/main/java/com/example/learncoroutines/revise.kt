package com.example.learncoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    println("Start program -> ${Thread.currentThread().name}")

    // blocking code
//    thread { //-> for creating another thread
//        Thread.sleep(2000)
//        println("Task -> ${Thread.currentThread().name}")
//    }


    // GlobalScope.launch -> launches a coroutine on a different thread, Thread.sleep(n) blocks the thread and also all coroutines on that thread
//    GlobalScope.launch {
//        Thread.sleep(5000) -> to simulate long running task in another thread.
//        println("Task -> ${Thread.currentThread().name}")
//    }


    GlobalScope.launch {
        println("Task started-> ${Thread.currentThread().name}")
        delay(2000)
        println("Task end-> ${Thread.currentThread().name}")
    }


    println("End program -> ${Thread.currentThread().name}")
    Thread.sleep(3000)
}