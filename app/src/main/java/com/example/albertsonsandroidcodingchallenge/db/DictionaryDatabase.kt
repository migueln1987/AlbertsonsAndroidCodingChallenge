package com.example.albertsonsandroidcodingchallenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.albertsonsandroidcodingchallenge.db.dao.DictionaryDAO
import com.example.albertsonsandroidcodingchallenge.model.RemoteDictionaryData

@Database(entities = [RemoteDictionaryData::class], version = 1, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {


    companion object {
        private const val DATABASE_NAME = "card_database"

        @Volatile
        private var INSTANCE: DictionaryDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { cardDatabase -> INSTANCE = cardDatabase }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            DictionaryDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    }

    abstract fun dictionaryDAO(): DictionaryDAO
}