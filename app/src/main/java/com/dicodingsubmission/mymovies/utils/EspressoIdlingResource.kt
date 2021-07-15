package com.dicodingsubmission.mymovies.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() = idlingResource.increment()
    fun decrement() =idlingResource.decrement()
    fun getIdlingResource(): IdlingResource = idlingResource

}