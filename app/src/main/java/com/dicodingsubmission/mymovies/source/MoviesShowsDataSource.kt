package com.dicodingsubmission.mymovies.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicodingsubmission.mymovies.model.Item
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity
import com.dicodingsubmission.mymovies.source.remote.ApiResponse
import com.dicodingsubmission.mymovies.utils.Resource

interface MoviesShowsDataSource {

    // Movies
    fun getOnPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>>
    fun setFavoriteMovie(movie : MovieEntity, isFavorite : Boolean)
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    // Tv Shows
    fun getTvOnTheAir(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getPopularTV(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowById(tvId: String): LiveData<Resource<TvShowEntity>>
    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean)
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>
}



