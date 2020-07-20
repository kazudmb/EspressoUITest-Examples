package com.dmb.espressouitest_examples

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.dmb.espressouitest_examples.ImageViewHasDrawableMatcher.hasDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private val TAG: String = "MainActivityTest"

    @Test
    fun test_showDialog_captureNameInput() {

        // GIVEN
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val EXPECTED_NAME = "Mitch"

        // Execute and Verify
        onView(withId(R.id.button_launch_dialog)).perform(click())

        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        onView(withText(R.string.text_ok)).perform(click())

        // make sure dialog is still visible (can't click ok without entering a name)
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        // enter some input
        onView(withId(R.id.md_input_message)).perform(typeText(EXPECTED_NAME))

        onView(withText(R.string.text_ok)).perform(click())

        // make sure dialog is gone
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        // confirm name is set to TextView in activity
        onView(withId(R.id.text_name)).check(matches(withText(EXPECTED_NAME)))
    }
}