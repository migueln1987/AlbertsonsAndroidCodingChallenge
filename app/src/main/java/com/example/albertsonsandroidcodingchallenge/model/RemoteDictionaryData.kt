package com.example.albertsonsandroidcodingchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RemoteDictionaryData{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var keyword:String=""
    var data:String=""

}