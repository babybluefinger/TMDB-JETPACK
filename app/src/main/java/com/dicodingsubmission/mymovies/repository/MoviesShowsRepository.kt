package com.dicodingsubmission.mymovies.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.dicodingsubmission.mymovies.model.Item
import com.dicodingsubmission.mymovies.model.TvMoviesResponse
import com.dicodingsubmission.mymovies.source.remote.ApiResponse
import com.dicodingsubmission.mymovies.source.MoviesShowsDataSource
import com.dicodingsubmission.mymovies.source.local.LocalDataSource
import com.dicodingsubmission.mymovies.source.local.NetworkBoundResource
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity
import com.dicodingsubmission.mymovies.source.remote.RemoteDataSource
import com.dicodingsubmission.mymovies.utils.AppExecutors
import com.dicodingsubmission.mymovies.utils.Resource

class MoviesShowsRepository(
    private val remoteRepository: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesShowsDataSource {

    companion object{
        @Volatile
        private var instance : MoviesShowsRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MoviesShowsRepository =
            instance ?: synchronized(this){
                MoviesShowsRepository(remoteDataSource, localDataSource, appExecutors).apply { instance = this }
            }
    }


    override fun getOnPlayingMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Item>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getOnPlayingMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<Item>>> =
                remoteRepository.getMoviesOnPlaying()

            override fun saveCallResult(data: List<Item>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data){
                    val movie = MovieEntity(response.id,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.overview,
                        false
                    )
                    movies.add(movie)
                }
                localDataSource.insertMovies(movies)
            }

        }.asLiveData()
    }

    override fun getPopularMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Item>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<Item>>> =
                remoteRepository.getMoviesPopular()

            override fun saveCallResult(data: List<Item>) {
                val movies = ArrayList<MovieEntity>()
                for (response in data){
                    val movie = MovieEntity(response.id,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.overview,
                        false)
                    movies.add(movie)
                }
                localDataSource.insertMovies(movies)
            }

        }.asLiveData()
    }

    override fun getMovieById(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Item>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieById(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null

            override fun createCall(): LiveData<ApiResponse<Item>> =
                remoteRepository.getMovieDetail(movieId)

            override fun saveCallResult(data: Item) {
                val movies = MovieEntity(
                    data.id,
                    data.title,
                    data.posterPath,
                    data.backdropPath,
                    data.releaseDate,
                    data.voteAverage,
                    data.overview,
                    false)
                localDataSource.updateMovie(movies, false)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, isFavorite)
        }
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getTvOnTheAir(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<Item>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getOnTheAirTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Item>>> {
                return remoteRepository.getTvOnTheAir()
            }

            override fun saveCallResult(data: List<Item>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (response in data){
                    val tvShow = TvShowEntity(response.id,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.firstAirDate,
                        response.voteAverage,
                        response.overview,
                        false
                    )
                    tvShows.add(tvShow)
                }
                localDataSource.insertTVShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getPopularTV(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<Item>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(20)
                    .setPageSize(20)
                    .build()
                return LivePagedListBuilder(localDataSource.getPopularTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Item>>> {
                return remoteRepository.getTvPopular()
            }

            override fun saveCallResult(data: List<Item>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (response in data){
                    val tvShow = TvShowEntity(response.id,
                        response.name,
                        response.posterPath,
                        response.backdropPath,
                        response.firstAirDate,
                        response.voteAverage,
                        response.overview,
                        false
                    )
                    tvShows.add(tvShow)
                }
                localDataSource.insertTVShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getTvShowById(tvId: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, Item>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTVShowById(tvId)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data != null
            }

            override fun createCall(): LiveData<ApiResponse<Item>> {
                return  remoteRepository.getTVDetail(tvId)
            }

            override fun saveCallResult(data: Item) {
                val tv = TvShowEntity(
                           id =  data.id,
                           name =  data.name,
                           poster_path =  data.posterPath,
                           backdrop_path =  data.backdropPath,
                           first_air_date =  data.firstAirDate,
                           vote_average =  data.voteAverage,
                           overview =  data.overview,
                            favorite = false)
                localDataSource.updateTvShow(tv, false)
            }
        }.asLiveData()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, isFavorite)
        }
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getFavoriteTVShow(), config).build()
    }
}