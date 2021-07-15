package com.dicodingsubmission.mymovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TvMoviesResponse(
	val results: List<Item>
)

@Parcelize
data class Item(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title") // movie
	val title : String,

	@field:SerializedName("name") // tv_show
	val name: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("first_air_date") // tv_show
	val firstAirDate: String,

	@field:SerializedName("release_date") //movie
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("overview")
	val overview: String,



):Parcelable
