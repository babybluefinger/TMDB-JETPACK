package com.dicodingsubmission.mymovies.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicodingsubmission.mymovies.R
import com.dicodingsubmission.mymovies.databinding.ActivityDetailMovieShowBinding
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity
import com.dicodingsubmission.mymovies.utils.Status
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel.Companion.MOVIE
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel.Companion.TV_SHOW
import com.dicodingsubmission.mymovies.viewmodel.ViewModelFactory

class DetailItemActivity : AppCompatActivity(){

    companion object{
        const val KEY_ID = "id"
        const val KEY_DETAIL ="type"
    }

    private lateinit var binding : ActivityDetailMovieShowBinding
    private lateinit var viewModel: TvMovieViewModel
    private var category : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityDetailMovieShowBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))[TvMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null){

            val id = extras.getString(KEY_ID)
            category = extras.getString(KEY_DETAIL)

            if (id != null && category != null){
                viewModel.setDetailBoth(id, category.toString())
                setUpFavorite()
                if(category == TV_SHOW){
                    viewModel.getTvShowById().observe(this, {tvShow ->
                        when(tvShow.status){
                            Status.LOADING-> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                if (tvShow.data != null){
                                    loading()
                                    showDetailTv(tvShow.data)
                                }
                            }
                            Status.ERROR ->{
                                loading()
                                Toast.makeText(applicationContext, "Unable to Load", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                } else {
                    viewModel.getMovieById().observe(this, { movie ->
                        when(movie.status){
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS ->{
                                if (movie.data != null){
                                    loading()
                                    showMovieDetail(movie.data)

                                }
                            }
                            Status.ERROR ->{
                                loading()
                                Toast.makeText(applicationContext, "Unable to Load", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                }
            }

        }

        binding.buttonFavorite.setOnClickListener {
            if (category == TV_SHOW){
                viewModel.setFavoriteTvShow()
            }
            else if (category == MOVIE){
                viewModel.setFavoriteMovie()
            }
        }

    }

    private fun loading(){
        binding.progressBar.visibility = View.GONE
        binding.content.visibility = View.VISIBLE
    }

    private fun showMovieDetail(movie : MovieEntity) {
        binding.apply {
            Glide.with(this@DetailItemActivity).load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}").into(backdrop)
            Glide.with(this@DetailItemActivity).load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(poster)
            title.text = movie.title
            release.text = movie.release_date
            rating.text = movie.vote_average.toString()
            overview.text = movie.overview
        }
    }

    private fun showDetailTv(tvShow : TvShowEntity) {
        binding.apply {
            Glide.with(this@DetailItemActivity).load("https://image.tmdb.org/t/p/w500${tvShow.backdrop_path}").into(backdrop)
            Glide.with(this@DetailItemActivity).load("https://image.tmdb.org/t/p/w500${tvShow.poster_path}").into(poster)
            title.text = tvShow.name
            release.text = tvShow.first_air_date
            rating.text = tvShow.vote_average.toString()
            overview.text = tvShow.overview
        }

    }

    private fun setUpFavorite() {
        if (category == TV_SHOW){
            viewModel.getTvShowById().observe(this, {tvShow ->
                when (tvShow.status) {
                    Status.LOADING-> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS->{
                        if(tvShow.data != null){
                            loading()
                            val isFavorite = tvShow.data.favorite
                            setFavoriteState(isFavorite)
                        }
                    }
                    Status.ERROR -> {
                        loading()
                        Toast.makeText(applicationContext, "Unable to Load", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }else {
            viewModel.getMovieById().observe(this, {movie ->
                when(movie.status){
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if(movie.data != null){
                            loading()
                            val isFavorite = movie.data.favorite
                            setFavoriteState(isFavorite)
                        }
                    }
                    Status.ERROR -> {
                        loading()
                        Toast.makeText(applicationContext, "Unable to Load", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }

    private fun setFavoriteState(favorite: Boolean) {
        val faButton = binding.buttonFavorite
        if (favorite){
            faButton.setImageResource(R.drawable.ic__favorite)
        }
        else{
            faButton.setImageResource(R.drawable.ic_baseline_unfavorite)
        }
    }
}

