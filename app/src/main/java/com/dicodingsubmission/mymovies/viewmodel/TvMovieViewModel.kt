package com.dicodingsubmission.mymovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicodingsubmission.mymovies.repository.MoviesShowsRepository
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity
import com.dicodingsubmission.mymovies.utils.Resource


class TvMovieViewModel(private val moviesShowsRepository: MoviesShowsRepository) : ViewModel() {

    companion object{
        const val MOVIE = "movie"
        const val TV_SHOW = "show"
    }
    private lateinit var tvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var movie: LiveData<Resource<MovieEntity>>

    /**set up detail*/
    fun setDetailBoth(id: String, key: String){
        when(key){
            TV_SHOW -> tvShow = moviesShowsRepository.getTvShowById(id)
            MOVIE -> movie = moviesShowsRepository.getMovieById(id)
        }
    }

    /**movie list*/
    fun getOnPlayingMovies() : LiveData<Resource<PagedList<MovieEntity>>> = moviesShowsRepository.getOnPlayingMovies()
    fun getPopularMovies() : LiveData<Resource<PagedList<MovieEntity>>> = moviesShowsRepository.getPopularMovies()

    // detail
    fun getMovieById() = movie
    fun setFavoriteMovie(){
        val resourceMovie = movie.value
        if(resourceMovie?.data != null){
            val isFavorite = !resourceMovie.data.favorite
            moviesShowsRepository.setFavoriteMovie(resourceMovie.data, isFavorite)
        }
    }

    // favorite
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = moviesShowsRepository.getFavoriteMovie()
    fun setFavMovie(movieEntity: MovieEntity){
        val newState = !movieEntity.favorite
        moviesShowsRepository.setFavoriteMovie(movieEntity, newState)
    }

    /**list tv Show*/
    fun getTvOnTheAir() : LiveData<Resource<PagedList<TvShowEntity>>> = moviesShowsRepository.getTvOnTheAir()
    fun getPopularTV() : LiveData<Resource<PagedList<TvShowEntity>>> = moviesShowsRepository.getPopularTV()

    //detail
    fun getTvShowById() = tvShow
    fun setFavoriteTvShow(){
        val resourceTvShow = tvShow.value
        if(resourceTvShow?.data != null){
            val isFavorite = !resourceTvShow.data.favorite
            moviesShowsRepository.setFavoriteTvShow(resourceTvShow.data, isFavorite)
        }
    }

    //favorite
    fun getFavoriteTvShow() : LiveData<PagedList<TvShowEntity>> = moviesShowsRepository.getFavoriteTvShow()
    fun setFavTvShow(tvShowEntity: TvShowEntity){
        val newState = !tvShowEntity.favorite
        moviesShowsRepository.setFavoriteTvShow(tvShowEntity, newState)
    }
}