package com.dnieln7.testing.kotlin

import kotlinx.coroutines.*

fun main() = runBlocking {
    Concurrent(this).start()
}

class Concurrent(private val scope: CoroutineScope) {

    var currentValue: Int? = null

    var timer: Job? = null

    fun start() {
        currentValue = 0

        timer = scope.launch {
            while (true) {
                if (currentValue != null) {
                    delay(500)
                    println("CURRENT VALUE DIRECT ACCESS $currentValue")
                }

                delay(1000)
            }
        }

        scope.launch {
            while (true) {

                currentValue?.let {
                    delay(500)
                    println("CURRENT VALUE LET $it")
                    println("CURRENT VALUE LET DIRECT ACCESS $currentValue")
                }

                delay(1000)
            }
        }

        scope.launch {
            while (currentValue != 10) {
                currentValue = (currentValue ?: 0) + 1

                delay(250)
            }
        }
    }
}