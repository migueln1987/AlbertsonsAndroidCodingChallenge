package com.example.albertsonsandroidcodingchallenge.network_api.services

import com.example.albertsonsandroidcodingchallenge.network_api.network_utils.NetworkURLS
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceRequest {


    @GET(NetworkURLS.DICTIONARY_URL)
    fun getMeanings(@Query("sf") acronym: String): Call<ResponseBody>
}