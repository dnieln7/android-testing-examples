package com.dnieln7.testing.ui.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dnieln7.testing.R
import kotlinx.coroutines.*
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get: Rule() // This rule must execute before each test
    val activity = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginWithAdmin() {
        onView(withId(R.id.email)).perform(typeText("admin@gmail.com"))
        onView(withId(R.id.password)).perform(typeText("admin@password"))
        onView(withId(R.id.login)).perform(click())

        runBlocking {
            delay(3500)
            onView(withId(R.id.error)).check(matches(not(isDisplayed())))
        }
    }
}