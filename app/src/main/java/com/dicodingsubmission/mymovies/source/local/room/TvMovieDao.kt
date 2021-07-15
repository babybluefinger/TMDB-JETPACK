package com.dicodingsubmission.mymovies.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity

@Dao
interface TvMovieDao {
    //Movie
    @Transaction
    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

    @Query("SELECT * FROM favorite_movies")
    fun getOnPlayingMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM favorite_movies ")
    fun getPopularMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieEntity>)

    @Update
    fun updateMovie(movie : MovieEntity)

    @Query("SELECT * FROM favorite_movies WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    //Tv Show
    @Transaction
    @Query("SELECT * FROM favorite_tv_show WHERE id = :id")
    fun getTVShowById(id: String): LiveData<TvShowEntity>

    @Query("SELECT * FROM favorite_tv_show")
    fun getTvOnTheAir(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM favorite_tv_show")
    fun getPopularTV(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movieEntity: List<TvShowEntity>)

    @Update
    fun updateTvShow(movie: TvShowEntity)

    @Query("SELECT * FROM favorite_tv_show Where favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvShowEntity>

}