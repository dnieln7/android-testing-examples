package com.dnieln7.testing.ui.hilt.books.fragment

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dnieln7.testing.R
import com.dnieln7.testing.androidUtil.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class BookListFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun clickItem_navigateToDetails() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<BookListFragment> {
            navController.setGraph(R.navigation.nav_graph_books)
            Navigation.setViewNavController(requireView(), navController)
        }

        runBlocking {
            delay(5000)

            onView(withId(R.id.books)).perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
            )

            assertThat(navController.currentDestination?.id).isEqualTo(R.id.bookDetailFragment)
        }
    }
}