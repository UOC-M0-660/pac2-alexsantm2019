package edu.uoc.pac2

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import edu.uoc.pac2.ui.BookListActivity
import junit.framework.Assert.assertTrue
import org.hamcrest.Matchers.endsWith
import org.junit.Rule
import org.junit.Test


/**
 * Created by alex on 04/10/2020.
 */

@LargeTest
class Ex7Test {

    @get:Rule
    val activityRule = ActivityScenarioRule(BookListActivity::class.java)

    @Test
    fun containsAdBanner() {
        onView(withClassName(endsWith("AdView"))).check { view, _ ->
            assertTrue(view != null)
            assertTrue(view.visibility == View.VISIBLE)
        }
    }
}