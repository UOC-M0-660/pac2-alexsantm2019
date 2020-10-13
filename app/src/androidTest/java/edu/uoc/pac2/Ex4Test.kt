package edu.uoc.pac2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.google.android.material.appbar.CollapsingToolbarLayout
import edu.uoc.pac2.ui.BookListActivity
import org.hamcrest.Matchers
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


/**
 * Created by alex on 04/10/2020.
 */

@LargeTest
class Ex4Test {

    @get:Rule
    val activityRule = ActivityScenarioRule(BookListActivity::class.java)

    @Test
    fun listItemOpensDetail() {
        onView(withId(R.id.book_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(TestData.bookListPosition, click()))
        // Opens Detail Activity
        Thread.sleep(500L)
        onView(withClassName(Matchers.endsWith("CollapsingToolbarLayout"))).check { view, _ ->
            assertTrue((view as CollapsingToolbarLayout).title == TestData.book.title)
        }
        onView(withText(TestData.book.author)).check(matches(isDisplayed()))
        onView(withText(TestData.book.description)).check(matches(isDisplayed()))
    }
}