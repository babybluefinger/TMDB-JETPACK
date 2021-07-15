package com.dicodingsubmission.mymovies.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicodingsubmission.mymovies.R
import com.dicodingsubmission.mymovies.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get: Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun loadTVShows(){
        onView(withId(R.id.fragment_tv)).perform(ViewActions.swipeUp())
        onView(withId(R.id.rv_tvOnTheAir)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tvOnTheAir)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
        onView(withId(R.id.rv_popularTv)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_popularTv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
    }

    @Test
    fun loadMovies(){
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.fragment_movies)).perform(ViewActions.swipeUp())
        onView(withId(R.id.rvRecommendationMovies)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rvRecommendationMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
        onView(withId(R.id.rv_popularMovies)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_popularMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))

    }

    @Test
    fun loadDetailTvOnTheAir(){
        onView(withId(R.id.rv_tvOnTheAir)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun loadDetailTvPopular(){
        onView(withId(R.id.fragment_tv)).perform(ViewActions.swipeUp())
        onView(withId(R.id.rv_popularTv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun loadDetailMovieOnPlaying(){
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rvRecommendationMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun loadDetailPopularMovie(){
        onView(withText("Movies")).perform(click())
        onView(withId(R.id.rv_popularMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

}