package com.dnieln7.testing.kotlin

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Channels {
    private val _events = Channel<Int>()
    val events =  _events.consumeAsFlow()

    suspend fun start() {
        _events.send(System.currentTimeMillis().toInt())
    }
}


fun main() {
    runBlocking {
        val cha = Channels()

        launch {
            cha.start()
        }

        delay(1000)

        launch {
            cha.events.collect { println(it) }
        }

        cha.events.collect { println(it) } // Only one collector

    }
}