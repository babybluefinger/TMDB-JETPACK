package com.dicodingsubmission.mymovies.ui.home.tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingsubmission.mymovies.databinding.FragmentTvShowsBinding
import com.dicodingsubmission.mymovies.utils.Status
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel
import com.dicodingsubmission.mymovies.viewmodel.ViewModelFactory

class TVShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      binding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){
            showTvOnTheAir()
            showPopularTV()
        }

    }

    private fun showTvOnTheAir() {
        val tvInTheAirTVAdapter = OnTheAirTVAdapter()
        val viewModel =  ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[TvMovieViewModel::class.java]
        viewModel.getTvOnTheAir().observe(viewLifecycleOwner,{tvShows ->
            if (tvShows != null){
                when(tvShows.status){
                    Status.SUCCESS -> {
                        tvInTheAirTVAdapter.submitList(tvShows.data)
                        tvInTheAirTVAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context, "Unable to Load", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.rvTvOnTheAir.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = tvInTheAirTVAdapter
        }
    }

    private fun showPopularTV() {
        val popularTVAdapter = PopularTVAdapter()
        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[TvMovieViewModel::class.java]
        viewModel.getPopularTV().observe(viewLifecycleOwner, {tvShows ->
            if (tvShows != null){
                when(tvShows.status){
                    Status.SUCCESS -> {
                        popularTVAdapter.submitList(tvShows.data)
                        popularTVAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        Toast.makeText(context, "Unable to Load", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.rvPopularTv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularTVAdapter
        }
    }

}