package com.example.albertsonsandroidcodingchallenge.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.albertsonsandroidcodingchallenge.model.RemoteDictionaryData

@Dao
interface DictionaryDAO {

    @Insert
    fun insertCard(info: RemoteDictionaryData)

    @Query("SELECT * FROM RemoteDictionaryData")
    fun getAllCards(): LiveData<List<RemoteDictionaryData>>

    @Query("DELETE FROM RemoteDictionaryData")
    fun deleteAll()
}