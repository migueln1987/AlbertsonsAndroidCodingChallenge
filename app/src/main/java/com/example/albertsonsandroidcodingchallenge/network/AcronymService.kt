package com.example.albertsonsandroidcodingchallenge.network

import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("dictionary.py")
    fun getMeanings(@Query("sf") acronym: String)

}