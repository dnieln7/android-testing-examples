package com.dnieln7.testing.ui.hilt.books.fragment

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dnieln7.testing.R
import com.dnieln7.testing.androidUtil.launchFragmentInHiltContainer
import com.dnieln7.testing.model.book.Author
import com.dnieln7.testing.model.book.Book
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@HiltAndroidTest
class BookDetailFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun clickItem_navigateToDetails() {
        val navController = mock(NavController::class.java)

        val book = Book(
            id = 84,
            title = "Frankenstein; Or, The Modern Prometheus",
            authors = listOf(
                Author(
                    name = "Shelley, Mary Wollstonecraft",
                    birthYear = 1797,
                    deathYear = 1851
                )
            ),
            subjects = listOf(
                "Frankenstein's monster (Fictitious character) -- Fiction",
                "Frankenstein, Victor (Fictitious character) -- Fiction",
                "Gothic fiction",
                "Horror tales",
                "Monsters -- Fiction",
                "Science fiction",
                "Scientists -- Fiction"
            ),
            languages = listOf(
                "en"
            ),
            bookshelves = listOf(
                "Gothic Fiction",
                "Movie Books",
                "Precursors of Science Fiction",
                "Science Fiction by Women"
            ),
            copyright = false,
            mediaType = "Text",
            downloadCount = 75355
        )

        launchFragmentInHiltContainer<BookDetailFragment>(fragmentArgs = bundleOf("book" to book)) {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.return_to_collection)).perform(click())

        verify(navController).popBackStack()
    }
}