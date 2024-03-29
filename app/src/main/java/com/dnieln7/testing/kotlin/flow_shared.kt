package com.dnieln7.testing.kotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SharedFlows {
    private val _events = MutableSharedFlow<Int>()
    val events =  _events.asSharedFlow()

    suspend fun start() {
        _events.emit(System.currentTimeMillis().toInt())
    }
}


fun main() {
    runBlocking {
        val shared = SharedFlows()

        launch {
            shared.events.collect { println(it) }
        }

        launch {
            shared.events.collect { println("-$it") }
        }

        delay(1000)

        launch {
            shared.start()
        }

        delay(100)

        launch {
            shared.events.collect { println("-$it") }
        }
    }
}