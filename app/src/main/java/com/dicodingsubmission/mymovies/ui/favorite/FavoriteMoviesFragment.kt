package com.dicodingsubmission.mymovies.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dicodingsubmission.mymovies.R
import com.dicodingsubmission.mymovies.databinding.FragmentFavoriteMoviesBinding
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel
import com.dicodingsubmission.mymovies.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteMoviesBinding
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter
    private lateinit var viewModel : TvMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavoriteMovies)

        if (activity != null){
            viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity()))[TvMovieViewModel::class.java]

            favoriteMoviesAdapter = FavoriteMoviesAdapter()

            binding.apply {
                favoriteProgressBar.visibility = View.VISIBLE
                viewModel.getFavoriteMovie().observe(viewLifecycleOwner, {movies ->
                    favoriteProgressBar.visibility = View.GONE
                    favoriteMoviesAdapter.submitList(movies)
                    favoriteMoviesAdapter.notifyDataSetChanged()
                })

                rvFavoriteMovies.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = favoriteMoviesAdapter
                }
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        @Suppress("DEPRECATION")
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favoriteMoviesAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavMovie(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    movieEntity?.let { viewModel.setFavMovie(it) }
                }
                snackBar.show()
            }
        }
    })

}