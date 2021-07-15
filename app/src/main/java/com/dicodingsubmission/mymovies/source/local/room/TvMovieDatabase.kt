package com.dicodingsubmission.mymovies.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicodingsubmission.mymovies.source.local.entities.MovieEntity
import com.dicodingsubmission.mymovies.source.local.entities.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class TvMovieDatabase : RoomDatabase() {
    abstract fun tvMovieDao() : TvMovieDao

    companion object{
        @Volatile
        private var INSTANCE : TvMovieDatabase? = null

        fun getInstance(context: Context): TvMovieDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext,
                    TvMovieDatabase::class.java,
                    "TvMovieDatabase"
                ).build().apply { INSTANCE = this }
            }
    }
}