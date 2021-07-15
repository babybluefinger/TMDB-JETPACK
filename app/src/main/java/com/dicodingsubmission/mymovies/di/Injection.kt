package com.dicodingsubmission.mymovies.di

import android.content.Context
import com.dicodingsubmission.mymovies.repository.MoviesShowsRepository
import com.dicodingsubmission.mymovies.source.local.LocalDataSource
import com.dicodingsubmission.mymovies.source.local.room.TvMovieDatabase
import com.dicodingsubmission.mymovies.source.remote.RemoteDataSource
import com.dicodingsubmission.mymovies.utils.AppExecutors

object Injection {

    fun provideMoviesShowsRepository(context: Context) : MoviesShowsRepository {

        val database = TvMovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.tvMovieDao())
        val appExecutors = AppExecutors()

        return MoviesShowsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}
