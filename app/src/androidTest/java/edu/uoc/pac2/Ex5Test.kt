package edu.uoc.pac2

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import edu.uoc.pac2.ui.BookDetailActivity
import edu.uoc.pac2.ui.BookDetailFragment
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test


/**
 * Created by alex on 04/10/2020.
 */

@LargeTest
class Ex5Test {

    @get:Rule
    val activityRule = ActivityScenarioRule<BookDetailActivity>(
            Intent(ApplicationProvider.getApplicationContext(), BookDetailActivity::class.java).apply {
                putExtra(BookDetailFragment.ARG_ITEM_ID, TestData.book.uid)
            }
    )

    @Test
    fun sharesBooksWithOtherApps() {
        Intents.init()
        // Click
        onView(ViewMatchers.withClassName(Matchers.endsWith("FloatingActionButton"))).perform(click())
        // Check Intent
        intended(hasAction(Intent.ACTION_CHOOSER))
        Intents.release()
    }
}