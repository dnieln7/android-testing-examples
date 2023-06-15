package com.dnieln7.testing.kotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val numbers = makeInts()
        val letters = makeStr()

        coldFlowsOnlyEmitWithObservers(numbers)
        hotFlowEmitsEvenWithoutObservers(numbers)
        convertToHotFlow(numbers)

        // zip applies to each pair of values cancels when one of the 2 flows finishes
        val decimals = numbers.zip(letters) { number, letter ->
            number * letter.toDouble()
        }

        decimals.collect { println("first -> $it") }

        delay(5000)

        decimals.collect { println("second -> $it") } // starts from start if it's collected again

        val flow = flowOf(1, 2, 3).onEach { delay(200) }
        val flow2 = flowOf("a", "b", "c", "d").onEach { delay(15) }

        // combine applies to the most recently emitted values if d was the most recent, it will combine with 1, 2 and 3
        flow.combine(flow2) { i, s -> i.toString() + s }.collect {
            println(it)
        }
    }
}

fun coldFlowsOnlyEmitWithObservers(numbers: Flow<Int>) = runBlocking {
    val job = launch {
        numbers.collect { println("cancelable -> $it") }
    }

    delay(5000)

    job.cancel()

    delay(5000)

    launch {
        numbers.collect { println("no cancelable -> $it") }
    }
}

fun hotFlowEmitsEvenWithoutObservers(numbers: Flow<Int>)  = runBlocking {
    val state = numbers.stateIn(this)

    val job = launch {
        state.collect { println("cancelable -> $it") }
    }

    delay(5000)

    job.cancel()

    delay(5000)

    launch {
        state.collect { println("no cancelable -> $it") }
    }
}

fun convertToHotFlow(numbers: Flow<Int>) = runBlocking {
    val state = numbers.stateIn(this)

    launch {
        state.collect { println("state 1 -> $it") }
    }

    delay(10000)

    launch {
        state.collect { println("state 2 -> $it") }
    }

    val share = numbers.shareIn(this, SharingStarted.Eagerly, replay = 2)

    println("firstOrNull 1: ${share.firstOrNull()}")
    delay(5000)
    println("firstOrNull 2: ${share.firstOrNull()}")

    launch {
        share.collect { println("share 1 -> $it") }
    }

    println("firstOrNull 3: ${share.firstOrNull()}")
    delay(5000)
    println("firstOrNull 4: ${share.firstOrNull()}")

    launch {
        share.collect { println("share 2 -> $it") }
    }
}

fun makeInts() = flow {
    for (i in 1..10) {
        delay(1000)
        println("Emitting $i ...")
        emit(i)
    }
}

fun makeStr() = flow {
    for (i in 10..20) {
        delay(1000)
        emit(i.toString())
    }
}