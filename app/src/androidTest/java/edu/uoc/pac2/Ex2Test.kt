package edu.uoc.pac2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import edu.uoc.pac2.ui.BookListActivity
import org.junit.Rule
import org.junit.Test


/**
 * Created by alex on 04/10/2020.
 */

@LargeTest
class Ex2Test {

    @get:Rule
    val activityRule = ActivityScenarioRule(BookListActivity::class.java)

    @Test
    fun listContainsBook() {
        Thread.sleep(TestData.networkWaitingMillis)
        onView(withText(TestData.book.title)).check(matches(isDisplayed()))
    }
}