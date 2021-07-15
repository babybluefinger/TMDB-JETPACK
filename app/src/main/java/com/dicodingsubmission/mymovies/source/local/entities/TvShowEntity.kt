package com.dicodingsubmission.mymovies.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tv_show")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name : String,
    var poster_path : String,
    var backdrop_path : String,
    var first_air_date : String,
    var vote_average : Double,
    var overview : String,
    var favorite : Boolean = false
)