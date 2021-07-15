//package com.dicodingsubmission.mymovies.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.dicodingsubmission.mymovies.model.Item
//import com.dicodingsubmission.mymovies.source.MoviesShowsDataSource
//import com.dicodingsubmission.mymovies.source.remote.RemoteDataSource
//
//class FakeRepository(private val remoteDataSource: RemoteDataSource) : MoviesShowsDataSource{
//
//    override fun getOnPlayingMovies(): LiveData<List<Item>> {
//        val movies = MutableLiveData<List<Item>>()
//        remoteDataSource.getRecommendationsMovies(object : RemoteDataSource.LoadListCallback{
//            override fun onResponse(response: List<Item>) {
//                movies.postValue(response)
//            }
//
//        })
//        return movies
//    }
//
//    override fun getPopularMovies(): LiveData<List<Item>> {
//        val movies = MutableLiveData<List<Item>>()
//        remoteDataSource.getMoviesPopular(object : RemoteDataSource.LoadListCallback{
//            override fun onResponse(response: List<Item>) {
//                movies.postValue(response)
//            }
//
//        })
//        return movies
//    }
//
//    override fun getTvOnTheAir(): LiveData<List<Item>> {
//        val tv = MutableLiveData<List<Item>>()
//        remoteDataSource.getTvOnTheAir(object : RemoteDataSource.LoadListCallback{
//            override fun onResponse(response: List<Item>) {
//                tv.postValue(response)
//            }
//
//        })
//        return tv
//    }
//
//    override fun getPopularTV(): LiveData<List<Item>> {
//        val tv = MutableLiveData<List<Item>>()
//        remoteDataSource.getTvPopular(object : RemoteDataSource.LoadListCallback{
//            override fun onResponse(response: List<Item>) {
//                tv.postValue(response)
//            }
//
//        })
//        return tv
//    }
//
//    override fun getTvDetail(tvId: String): LiveData<Item> {
//        val tv = MutableLiveData<Item>()
//        tvId?.let {
//            remoteDataSource.getTVDetail(it, object : RemoteDataSource.LoadDetail{
//                override fun onResponse(response: Item) {
//                    tv.postValue(response)
//                }
//
//            })
//        }
//        return tv
//    }
//
//    override fun getMovieDetail(movieId: String): LiveData<Item> {
//        val tv = MutableLiveData<Item>()
//        movieId?.let {
//            remoteDataSource.getMovieDetail(it, object : RemoteDataSource.LoadDetail{
//                override fun onResponse(response: Item) {
//                    tv.postValue(response)
//                }
//
//            })
//        }
//        return tv
//    }
//
//}