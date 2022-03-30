package com.dnieln7.testing.kotlin

fun main() {
    flow()
    stateFlow()
    sharedFlow()
}

fun flow() {
    println("FLOW")
    println("Cold flow - Only emits values when has a least one observer")
    println("Keeps all values that have been emitted")
    println("If a new object observes, it will receive all values starting from the first")
}

fun stateFlow() {
    println("STATE FLOW")
    println("Hot flow - Emits values event if there are no observers")
    println("State holder - Only stores 1 value, the last value")
}

fun sharedFlow() {
    println("SHARED FLOW")
    println("Hot flow - Emits values event if there are no observers")
    println("It's an event bus")
    println("New values will be received but if it is not emitting values the observers will not get anything when subscribing")
    println("Can be configured of how many values you want to receive when subscribing - 1 as state flow - 0 - default")
    println("can configure buffer (saved values witch are not processed yet)")
}

fun channel() {
    println("CHANNEL")
    println("Hot flow - Emits values event if there are no observers")
    println("It's value is for single use")
    println("If a observer gets the emitted value, it will no longer be available for other observers")
}

