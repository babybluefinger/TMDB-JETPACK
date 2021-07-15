//package com.dicodingsubmission.mymovies.repository
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.dicodingsubmission.mymovies.source.remote.RemoteDataSource
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito
//import org.mockito.Mockito.*
//
//class MoviesShowsRepositoryTest {
//
//    @get: Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val remoteRepository = mock(RemoteDataSource::class.java)
//    private val moviesShowsRepository = FakeRepository(remoteRepository)
//
//    private val moviesOnPlayingResponse = FakeData.getOnPlayingMovie()
//    private val movieOnPlayingId = FakeData.getOnPlayingMovie()[0].id.toString()
//    private val moviesPopular = FakeData.getPopularMovie()
//    private val moviePopularId = FakeData.getPopularMovie()[0].id.toString()
//    private val tvOnTheAirResponse = FakeData.getTVOnTheAir()
//    private val tvOnTheAirId = FakeData.getTVOnTheAir()[0].id.toString()
//    private val tvPopularResponse = FakeData.getPopularTV()
//    private val tvPopularId = FakeData.getPopularTV()[0].id.toString()
//
//    private fun <T> any(): T {
//        Mockito.any<T>()
//        return uninitialized()
//    }
//    private fun <T> uninitialized(): T = null as T
//
//    private fun<T> eq(obj : T): T = Mockito.eq<T>(obj)
//
//    @Test
//    fun getOnPlayingMovies() {
//        doAnswer{
//            (it.arguments[0] as RemoteDataSource.LoadListCallback)
//                .onResponse(moviesOnPlayingResponse)
//            null
//        }.`when`(remoteRepository).getMoviesOnPlaying(any())
//        val movies = LiveDataTest.getValue(moviesShowsRepository.getOnPlayingMovies())
//        verify(remoteRepository).getMoviesOnPlaying(any())
//        assertNotNull(movies)
//        assertEquals(moviesOnPlayingResponse.size.toLong(), movies.size.toLong())
//    }
//
//    @Test
//    fun getPopularMovies() {
//        doAnswer{
//            (it.arguments[0] as RemoteDataSource.LoadListCallback)
//                .onResponse(moviesOnPlayingResponse)
//            null
//        }.`when`(remoteRepository).getMoviesPopular(any())
//        val movies = LiveDataTest.getValue(moviesShowsRepository.getPopularMovies())
//        verify(remoteRepository).getMoviesPopular(any())
//        assertNotNull(movies)
//        assertEquals(moviesOnPlayingResponse.size.toLong(), movies.size.toLong())
//    }
//
//    @Test
//    fun getTvOnTheAir() {
//        doAnswer{
//            (it.arguments[0] as RemoteDataSource.LoadListCallback)
//                .onResponse(tvOnTheAirResponse)
//            null
//        }.`when`(remoteRepository).getTvOnTheAir(any())
//        val tv = LiveDataTest.getValue(moviesShowsRepository.getTvOnTheAir())
//        verify(remoteRepository).getTvOnTheAir(any())
//        assertNotNull(tv)
//        assertEquals(tvOnTheAirResponse.size.toLong(), tv.size.toLong())
//    }
//
//    @Test
//    fun getPopularTV() {
//        doAnswer{
//            (it.arguments[0] as RemoteDataSource.LoadListCallback)
//                .onResponse(tvPopularResponse)
//            null
//        }.`when`(remoteRepository).getTvPopular(any())
//        val tv = LiveDataTest.getValue(moviesShowsRepository.getPopularTV())
//        verify(remoteRepository).getTvPopular(any())
//        assertNotNull(tv)
//        assertEquals(tvPopularResponse.size.toLong(), tv.size.toLong())
//    }
//
//    @Test
//    fun getTvDetail() {
//        doAnswer {
//            (it.arguments[0] as RemoteDataSource.LoadDetail)
//                .onResponse(tvOnTheAirResponse[0])
//            null
//        }.`when`(remoteRepository).getTVDetail(eq(tvOnTheAirId), any())
//    }
//
//    @Test
//    fun getPopularTvDetail() {
//        doAnswer {
//            (it.arguments[0] as RemoteDataSource.LoadDetail)
//                .onResponse(tvPopularResponse[0])
//            null
//        }.`when`(remoteRepository).getTVDetail(eq(tvPopularId), any())
//    }
//
//    @Test
//    fun getMovieDetail() {
//        doAnswer {
//            (it.arguments[0] as RemoteDataSource.LoadDetail)
//                .onResponse(moviesOnPlayingResponse[0])
//            null
//        }.`when`(remoteRepository).getMovieDetail(eq(movieOnPlayingId), any())
//    }
//
//    @Test
//    fun getPopularMovieDetail() {
//        doAnswer {
//            (it.arguments[0] as RemoteDataSource.LoadDetail)
//                .onResponse(moviesPopular[0])
//            null
//        }.`when`(remoteRepository).getMovieDetail(eq(moviePopularId), any())
//    }
//}