package com.dnieln7.testing.kotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SharedFlows {
    private val _events = MutableSharedFlow<Int>(1)
    val events =  _events.asSharedFlow()

    suspend fun start() {
        _events.emit(System.currentTimeMillis().toInt())
    }
}


fun main() {
    runBlocking {
        val shared = SharedFlows()

        launch {
            shared.start()
        }

        delay(1000)

        shared.events.collect { println(it) }
    }
}