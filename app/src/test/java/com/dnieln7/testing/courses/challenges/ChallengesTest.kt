package com.dnieln7.testing.courses.challenges

import org.junit.Test
import com.google.common.truth.Truth.*

class ChallengesTest {

    @Test
    fun `fib(n) = fib(n-2) + fib(n-1) when n is greater than 3`() {
        val n = 4
        val first = Challenges.fib(n)
        val second = Challenges.fib(n - 2)
        val third = Challenges.fib(n - 1)

        assertThat(first).isEqualTo(second + third)
    }
}