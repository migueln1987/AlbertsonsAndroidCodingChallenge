package com.example.albertsonsandroidcodingchallenge.network_api.network_utils

interface NetworkResponseCallback {

    fun onSuccessResponse(jsonData: String, responseCode: Int)
    fun onError(errorCode: Int, errorData: String)
}
