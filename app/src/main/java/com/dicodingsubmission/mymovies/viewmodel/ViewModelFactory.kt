package com.dicodingsubmission.mymovies.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicodingsubmission.mymovies.di.Injection
import com.dicodingsubmission.mymovies.repository.MoviesShowsRepository


class ViewModelFactory private constructor(private val moviesShowsRepository: MoviesShowsRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideMoviesShowsRepository(context)).apply {
                    instance = this
                }
            }


    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(TvMovieViewModel::class.java) ->
                TvMovieViewModel(moviesShowsRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

}