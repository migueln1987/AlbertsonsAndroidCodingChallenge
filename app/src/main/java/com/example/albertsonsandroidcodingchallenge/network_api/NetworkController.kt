package com.example.albertsonsandroidcodingchallenge.network_api

import android.content.Context
import com.example.albertsonsandroidcodingchallenge.network_api.network_utils.NetworkResponseCallback
import com.example.albertsonsandroidcodingchallenge.network_api.network_utils.NetworkUtils
import com.example.albertsonsandroidcodingchallenge.network_api.services.ServiceRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkController constructor(val mContext: Context) {

    fun getListData(sf:String,callback: NetworkResponseCallback) {
        val service = NetworkUtils.buildRetrofit().create(ServiceRequest::class.java)
        val authenticationResponseCall = service.getMeanings(sf)
        authenticationResponseCall.enqueue(RetrofitServiceTask(callback))
    }

    private inner class RetrofitServiceTask(internal var networkResponseCallback: NetworkResponseCallback) :
        Callback<ResponseBody> {

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.code() == NetworkUtils.HTTP_SUCCESS) {
                networkResponseCallback.onSuccessResponse(NetworkUtils.getStringResponseFromRaw(response), response.code())
            } else {
                val errorMsg = NetworkUtils.getStringResponseFromRaw(response.errorBody()!!)

                networkResponseCallback.onError(response.code(), errorMsg ?: SERVER_ERROR)
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            networkResponseCallback.onError(NetworkUtils.HTTP_RETROFIT_FAILURE, t.localizedMessage)
        }
    }

    companion object {
        val TAG = NetworkController::class.simpleName
        val SERVER_ERROR = "Something went wrong on the server"
    }
}
