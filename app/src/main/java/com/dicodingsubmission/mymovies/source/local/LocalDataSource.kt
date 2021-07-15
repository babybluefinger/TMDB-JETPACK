package com.dicodingsubmission.mymovies.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity
import com.dicodingsubmission.mymovies.source.local.room.TvMovieDao

class LocalDataSource private constructor(private val tvMovieDao: TvMovieDao){

    companion object{
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(tvMovieDao: TvMovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(tvMovieDao).apply { INSTANCE = this }
    }

    /*** MOVIES*/
    fun getOnPlayingMovies() :DataSource.Factory<Int, MovieEntity> =
        tvMovieDao.getOnPlayingMovies()

    fun getPopularMovies() :DataSource.Factory<Int, MovieEntity> =
        tvMovieDao.getPopularMovies()

    fun getMovieById(movieId: String) : LiveData<MovieEntity> =
        tvMovieDao.getMovieById(movieId)

    fun insertMovies(movies: List<MovieEntity>) =
        tvMovieDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, isFavorite : Boolean){
        movie.favorite = isFavorite
        tvMovieDao.updateMovie(movie)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> =
        tvMovieDao.getFavoriteMovie()

    fun updateMovie(movie: MovieEntity, isFavorite: Boolean){
        movie.favorite = isFavorite
        tvMovieDao.updateMovie(movie)
    }

    /***TV SHOWS*/
    fun getOnTheAirTvShows() : DataSource.Factory<Int, TvShowEntity> =
        tvMovieDao.getTvOnTheAir()

    fun getPopularTvShows() : DataSource.Factory<Int, TvShowEntity> =
        tvMovieDao.getPopularTV()

    fun getTVShowById(tvId: String) : LiveData<TvShowEntity> =
        tvMovieDao.getTVShowById(tvId)

    fun insertTVShows(tvShows : List<TvShowEntity>) =
        tvMovieDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean){
        tvShow.favorite = isFavorite
        tvMovieDao.updateTvShow(tvShow)
    }

    fun getFavoriteTVShow() : DataSource.Factory<Int, TvShowEntity> =
        tvMovieDao.getFavoriteTv()

    fun updateTvShow(tvShow: TvShowEntity, isFavorite: Boolean){
        tvShow.favorite = isFavorite
        tvMovieDao.updateTvShow(tvShow)
    }

}