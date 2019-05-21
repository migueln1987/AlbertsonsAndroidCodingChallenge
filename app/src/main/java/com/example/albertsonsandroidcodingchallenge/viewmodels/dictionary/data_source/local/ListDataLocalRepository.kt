package com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source.local

import android.content.Context
import com.example.albertsonsandroidcodingchallenge.db.DictionaryDatabase
import com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source.ListDataSource

class ListDataLocalRepository private constructor(mContext: Context) : ListDataSource {

    init {
        val cardDatabase = DictionaryDatabase.invoke(mContext)
        val dictionaryDAO = cardDatabase.dictionaryDAO()
    }


    companion object {
        @Volatile
        private var INSTANCE: ListDataLocalRepository? = null

        fun getInstance(mContext: Context): ListDataLocalRepository? {
            if (INSTANCE == null) {
                synchronized(ListDataLocalRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ListDataLocalRepository(mContext)
                    }
                }
            }
            return INSTANCE
        }
    }
}
