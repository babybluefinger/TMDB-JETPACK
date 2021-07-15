package com.dicodingsubmission.mymovies.ui.home.movies

import android.R
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.dicodingsubmission.mymovies.databinding.ListNewMovieBinding
import com.dicodingsubmission.mymovies.model.Item
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.ui.DetailItemActivity
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel.Companion.MOVIE


class OnPlayingMoviesAdapter : PagedListAdapter<MovieEntity, OnPlayingMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListNewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null){
            holder.bind(movie)
        }
    }

    class ViewHolder(private val binding: ListNewMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${movieEntity.poster_path}")
                    .into(ivNewMovie)
                tvTitle.text = movieEntity?.title

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailItemActivity::class.java)
                    intent.putExtra(DetailItemActivity.KEY_ID, movieEntity.id.toString())
                    intent.putExtra(DetailItemActivity.KEY_DETAIL, MOVIE)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }
}

