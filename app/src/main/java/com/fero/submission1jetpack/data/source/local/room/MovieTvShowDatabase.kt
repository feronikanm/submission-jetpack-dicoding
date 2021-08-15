package com.fero.submission1jetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fero.submission1jetpack.data.source.local.entity.MovieEntity
import com.fero.submission1jetpack.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieTvShowDatabase : RoomDatabase() {
    abstract fun movieTvShowDao(): MovieTvShowDao

    companion object {

        @Volatile
        private var INSTANCE: MovieTvShowDatabase? = null

        fun getInstance(context: Context): MovieTvShowDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieTvShowDatabase::class.java,
                    "MovieTvShow.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}