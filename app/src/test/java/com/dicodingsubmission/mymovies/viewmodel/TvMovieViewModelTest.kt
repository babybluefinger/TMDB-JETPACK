//package com.dicodingsubmission.mymovies.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.dicodingsubmission.mymovies.model.Item
//import com.dicodingsubmission.mymovies.repository.FakeData
//import com.dicodingsubmission.mymovies.repository.MoviesShowsRepository
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.Mockito.verify
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class TvMovieViewModelTest {
//
//    private lateinit var viewModel: TvMovieViewModel
//
//    @Mock
//    private lateinit var repository: MoviesShowsRepository
//
//    @Mock
//    private lateinit var moviesObserver: Observer<List<Item>>
//
//    @Mock
//    private lateinit var detailObserver: Observer<Item>
//
//    @get: Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @Before
//    fun setUp(){
//        viewModel = TvMovieViewModel(repository)
//    }
//
//    @Test
//    fun getRecommendationMovies() {
//        val movies = MutableLiveData<List<Item>>()
//        movies.value = FakeData.getOnPlayingMovie()
//
//        `when`(repository.getOnPlayingMovies()).thenReturn(movies)
//        val movieEntity = viewModel.getRecommendationMovies().value
//        verify(repository).getOnPlayingMovies()
//        assertNotNull(movieEntity)
//        assertEquals(20, movieEntity?.size)
//
//        viewModel.getRecommendationMovies().observeForever(moviesObserver)
//        verify(moviesObserver).onChanged(FakeData.getOnPlayingMovie())
//    }
//
//    @Test
//    fun getPopularMovies() {
//        val movies = MutableLiveData<List<Item>>()
//        movies.value = FakeData.getPopularMovie()
//
//        `when`(repository.getPopularMovies()).thenReturn(movies)
//        val movieEntity = viewModel.getPopularMovies().value
//        verify(repository).getPopularMovies()
//        assertNotNull(movieEntity)
//        assertEquals(20, movieEntity?.size)
//
//        viewModel.getPopularMovies().observeForever(moviesObserver)
//        verify(moviesObserver).onChanged(FakeData.getPopularMovie())
//    }
//
//    @Test
//    fun getTvOnTheAir() {
//        val tv = MutableLiveData<List<Item>>()
//        tv.value = FakeData.getTVOnTheAir()
//
//        `when`(repository.getTvOnTheAir()).thenReturn(tv)
//        val tvEntity = viewModel.getTvOnTheAir().value
//        verify(repository).getTvOnTheAir()
//        assertNotNull(tvEntity)
//        assertEquals(20, tvEntity?.size)
//
//        viewModel.getTvOnTheAir().observeForever(moviesObserver)
//        verify(moviesObserver).onChanged(FakeData.getTVOnTheAir())
//    }
//
//    @Test
//    fun getPopularTV() {
//        val tv = MutableLiveData<List<Item>>()
//        tv.value = FakeData.getPopularTV()
//
//        `when`(repository.getPopularTV()).thenReturn(tv)
//        val tvEntity = viewModel.getPopularTV().value
//        verify(repository).getPopularTV()
//        assertNotNull(tvEntity)
//        assertEquals(20, tvEntity?.size)
//
//        viewModel.getPopularTV().observeForever(moviesObserver)
//        verify(moviesObserver).onChanged(FakeData.getPopularTV())
//    }
//
//    @Test
//    fun getTvDetail() {
//        val tv = MutableLiveData<Item>()
//        tv.value = FakeData.getTVOnTheAir()[0]
//
//        `when`(repository.getTvDetail(tv.value!!.id.toString()))
//            .thenReturn(tv)
//        viewModel.getTvDetail(tv.value!!.id.toString()).observeForever(detailObserver)
//        verify(repository).getTvDetail(tv.value!!.id.toString())
//
//        assertEquals(tv.value!!.id, viewModel.getTvDetail(tv.value!!.id.toString()).value?.id)
//        assertEquals(tv.value!!.name, viewModel.getTvDetail(tv.value!!.id.toString()).value?.name)
//        assertEquals(tv.value!!.backdropPath, viewModel.getTvDetail(tv.value!!.id.toString()).value?.backdropPath)
//        assertEquals(tv.value!!.posterPath, viewModel.getTvDetail(tv.value!!.id.toString()).value?.posterPath)
//        assertEquals(tv.value!!.firstAirDate, viewModel.getTvDetail(tv.value!!.id.toString()).value?.firstAirDate)
//        assertEquals(tv.value!!.voteAverage, viewModel.getTvDetail(tv.value!!.id.toString()).value?.voteAverage)
//        assertEquals(tv.value!!.overview, viewModel.getTvDetail(tv.value!!.id.toString()).value?.overview)
//    }
//
//    @Test
//    fun getPopularTvDetail() {
//        val tv = MutableLiveData<Item>()
//        tv.value = FakeData.getPopularTV()[0]
//
//        `when`(repository.getTvDetail(tv.value!!.id.toString()))
//            .thenReturn(tv)
//        viewModel.getTvDetail(tv.value!!.id.toString()).observeForever(detailObserver)
//        verify(repository).getTvDetail(tv.value!!.id.toString())
//
//        assertEquals(tv.value!!.id, viewModel.getTvDetail(tv.value!!.id.toString()).value?.id)
//        assertEquals(tv.value!!.name, viewModel.getTvDetail(tv.value!!.id.toString()).value?.name)
//        assertEquals(tv.value!!.backdropPath, viewModel.getTvDetail(tv.value!!.id.toString()).value?.backdropPath)
//        assertEquals(tv.value!!.posterPath, viewModel.getTvDetail(tv.value!!.id.toString()).value?.posterPath)
//        assertEquals(tv.value!!.firstAirDate, viewModel.getTvDetail(tv.value!!.id.toString()).value?.firstAirDate)
//        assertEquals(tv.value!!.voteAverage, viewModel.getTvDetail(tv.value!!.id.toString()).value?.voteAverage)
//        assertEquals(tv.value!!.overview, viewModel.getTvDetail(tv.value!!.id.toString()).value?.overview)
//    }
//
//
//    @Test
//    fun getMovieDetail() {
//        val movie = MutableLiveData<Item>()
//        movie.value = FakeData.getOnPlayingMovie()[0]
//
//        `when`(repository.getMovieDetail(movie.value!!.id.toString()))
//            .thenReturn(movie)
//        viewModel.getMovieDetail(movie.value!!.id.toString()).observeForever(detailObserver)
//        verify(repository).getMovieDetail(movie.value!!.id.toString())
//
//        assertEquals(movie.value!!.id, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.id)
//        assertEquals(movie.value!!.title, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.title)
//        assertEquals(movie.value!!.backdropPath, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.backdropPath)
//        assertEquals(movie.value!!.posterPath, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.posterPath)
//        assertEquals(movie.value!!.releaseDate, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.releaseDate)
//        assertEquals(movie.value!!.voteAverage, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.voteAverage)
//        assertEquals(movie.value!!.overview, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.overview)
//    }
//
//
//    @Test
//    fun getPopularMovieDetail() {
//        val movie = MutableLiveData<Item>()
//        movie.value = FakeData.getPopularMovie()[0]
//
//        `when`(repository.getMovieDetail(movie.value!!.id.toString()))
//            .thenReturn(movie)
//        viewModel.getMovieDetail(movie.value!!.id.toString()).observeForever(detailObserver)
//        verify(repository).getMovieDetail(movie.value!!.id.toString())
//
//        assertEquals(movie.value!!.id, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.id)
//        assertEquals(movie.value!!.title, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.title)
//        assertEquals(movie.value!!.backdropPath, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.backdropPath)
//        assertEquals(movie.value!!.posterPath, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.posterPath)
//        assertEquals(movie.value!!.releaseDate, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.releaseDate)
//        assertEquals(movie.value!!.voteAverage, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.voteAverage)
//        assertEquals(movie.value!!.overview, viewModel.getMovieDetail(movie.value!!.id.toString()).value?.overview)
//    }
//}