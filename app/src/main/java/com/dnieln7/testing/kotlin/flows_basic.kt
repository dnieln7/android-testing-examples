package com.dnieln7.testing.kotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val numbers = makeInts()
        val letters = makeStr()

        // zip applies to each pair of values cancels when one of the 2 flows finishes
        // combine applies to the most recently emitted values if d was the most recent, it will combine with 1, 2 and 3

        val decimals = numbers.zip(letters) { number, letter ->
            number * letter.toDouble()
        }

        decimals.collect { println("first -> $it") }

        delay(5000)

        decimals.collect { println("second -> $it") } // starts from start if it's collected again

        val flow = flowOf(1, 2, 3).onEach { delay(200) }
        val flow2 = flowOf("a", "b", "c", "d").onEach { delay(15) }
        flow.combine(flow2) { i, s -> i.toString() + s }.collect {
            println(it) // Will print "1a 2b 3c"
        }
    }
}

fun makeInts() = flow {
    for (i in 1..10) {
        delay(1000)
        emit(i)
    }
}

fun makeStr() = flow {
    for (i in 1..10) {
        delay(1000)
        emit(i.toString())
    }
}