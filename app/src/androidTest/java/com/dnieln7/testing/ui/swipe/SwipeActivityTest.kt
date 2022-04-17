package com.dnieln7.testing.ui.swipe

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dnieln7.testing.R
import com.dnieln7.testing.ui.swipe.adapter.SwipeAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SwipeActivityTest {

    @get:Rule
    val activityScenario = ActivityScenarioRule(SwipeActivity::class.java)

    @Test
    fun swipeToLeft_DeletesItem(): Unit = runBlocking {

        delay(3000)

        onView(withId(R.id.books)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SwipeAdapter.SwipeViewHolder>(0, swipeLeft())
        )

        delay(3000)

        onView(withId(R.id.books)).check(matches(hasChildCount(1)))
    }
}