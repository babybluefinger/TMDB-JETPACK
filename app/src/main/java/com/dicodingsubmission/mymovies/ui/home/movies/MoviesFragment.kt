package com.dicodingsubmission.mymovies.ui.home.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingsubmission.mymovies.databinding.FragmentMoviesBinding
import com.dicodingsubmission.mymovies.utils.Status
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel
import com.dicodingsubmission.mymovies.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(){

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            showOnPlayingMovies()
            showPopularMovies()
        }
    }


    private fun showOnPlayingMovies() {
        val onPlayingMoviesAdapter = OnPlayingMoviesAdapter()
        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[TvMovieViewModel::class.java]
        viewModel.getOnPlayingMovies().observe(viewLifecycleOwner, {movies ->
            if (movies != null){
                when(movies.status){
                    Status.SUCCESS -> {
                        onPlayingMoviesAdapter.submitList(movies.data)
                        onPlayingMoviesAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Unable to Load", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.rvRecommendationMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = onPlayingMoviesAdapter
        }
    }

    private fun showPopularMovies() {
        val popularAdapter = PopularMoviesAdapter()
        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[TvMovieViewModel::class.java]
        viewModel.getPopularMovies().observe(viewLifecycleOwner, {movies ->
            if (movies != null){
                when(movies.status){
                    Status.SUCCESS -> {
                        popularAdapter.submitList(movies.data)
                        popularAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Unable to Load", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularAdapter
        }

    }

}

