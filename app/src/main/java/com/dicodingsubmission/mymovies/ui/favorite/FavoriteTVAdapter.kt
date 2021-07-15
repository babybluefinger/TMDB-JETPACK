package com.dicodingsubmission.mymovies.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicodingsubmission.mymovies.databinding.ListMostPopularBinding
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity
import com.dicodingsubmission.mymovies.ui.DetailItemActivity
import com.dicodingsubmission.mymovies.viewmodel.TvMovieViewModel

class FavoriteTVAdapter : PagedListAdapter<TvShowEntity, FavoriteTVAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>(){
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListMostPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null){
            holder.bind(tvShow)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    class ViewHolder(private val binding: ListMostPopularBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind(tvShow: TvShowEntity) {
            binding.apply {
                Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500/${tvShow.poster_path}").into(imageViewGeneral)
                titleMoviesAndShows.text = tvShow.name

                itemView.setOnClickListener{
                    val intent = Intent(itemView.context, DetailItemActivity::class.java)
                    with(intent){
                        intent.putExtra(DetailItemActivity.KEY_ID, tvShow.id.toString())
                        intent.putExtra(DetailItemActivity.KEY_DETAIL, TvMovieViewModel.TV_SHOW)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }


    }

}