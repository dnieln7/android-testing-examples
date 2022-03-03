package com.dnieln7.testing.ui.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dnieln7.testing.R
import com.dnieln7.testing.ui.navigation.fragment.KeyboardFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationActivityTest {

    @Test
    fun fromKeyboardToKey() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val letterListScenario = launchFragmentInContainer<KeyboardFragment>(
            themeResId = R.style.Theme_TestingExamples
        )

        letterListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph_basic)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.key_sets)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

        assertEquals(navController.currentDestination?.id, R.id.keysFragment)
    }
}