package com.dicodingsubmission.mymovies.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class MovieEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var poster_path : String,
    var backdrop_path : String,
    var release_date : String,
    var vote_average : Double,
    var overview : String,
    var favorite : Boolean = false
)