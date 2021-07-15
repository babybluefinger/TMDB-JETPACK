package com.dicodingsubmission.mymovies.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicodingsubmission.mymovies.databinding.ContentDetailBinding
import com.dicodingsubmission.mymovies.databinding.FragmentFavoriteMoviesBinding
import com.dicodingsubmission.mymovies.databinding.ListMostPopularBinding
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.ui.DetailItemActivity
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel

class FavoriteMoviesAdapter: PagedListAdapter<MovieEntity, FavoriteMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.bind(movie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    class ViewHolder(private val binding: ListMostPopularBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieEntity) {
            binding.apply {
                Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500/${movie.poster_path}").into(imageViewGeneral)
                titleMoviesAndShows.text = movie.title

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailItemActivity::class.java)
                    intent.putExtra(DetailItemActivity.KEY_ID, movie.id.toString())
                    intent.putExtra(DetailItemActivity.KEY_DETAIL, TvMovieViewModel.MOVIE)
                    itemView.context.startActivity(intent)
                }
            }
        }


    }

}