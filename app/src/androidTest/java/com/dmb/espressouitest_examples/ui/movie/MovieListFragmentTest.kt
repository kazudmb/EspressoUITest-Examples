package com.dmb.espressouitest_examples.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.dmb.espressouitest_examples.R
import com.dmb.espressouitest_examples.data.FakeMovieData
import com.dmb.espressouitest_examples.data.Movie
import com.dmb.espressouitest_examples.data.source.MoviesDataSource
import com.dmb.espressouitest_examples.data.source.MoviesRemoteDataSource
import com.dmb.espressouitest_examples.factory.MovieFragmentFactory
import com.dmb.espressouitest_examples.ui.movie.MoviesListAdapter.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_selectItemList_isDetailFragmentVisible() {
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    @Test
    fun test_backNavigation_toMovieListFragment() {
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
        pressBack()
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_directiors)).perform(click())
        val directors = MOVIE_IN_TEST.directors
        val verifyDirectorsValue = DirectorsFragment.stringBuilderForDirectors(directors!!)
        onView(withId(R.id.directors_text)).check(matches(withText(verifyDirectorsValue)))
    }

    @Test
    fun test_navStarActorsFragment_validateStarActorsList() {
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition<MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())
        val starActors = MOVIE_IN_TEST.star_actors
        val verifyStarActorsValue = StarActorsFragment.stringBuilderForStarActors(starActors!!)
        onView(withId(R.id.star_actors_text)).check(matches(withText(verifyStarActorsValue)))
    }
}