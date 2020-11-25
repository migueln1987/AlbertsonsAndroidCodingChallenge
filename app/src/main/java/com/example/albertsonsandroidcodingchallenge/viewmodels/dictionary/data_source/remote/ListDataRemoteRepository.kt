package com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source.remote

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.albertsonsandroidcodingchallenge.model.DictionaryMeaning
import com.example.albertsonsandroidcodingchallenge.network_api.NetworkController
import com.example.albertsonsandroidcodingchallenge.network_api.network_utils.NetworkResponseCallback
import com.example.albertsonsandroidcodingchallenge.network_api.network_utils.NetworkUtils
import com.example.albertsonsandroidcodingchallenge.utils.IFailureCallback
import com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source.ListDataSource
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken

class ListDataRemoteRepository private constructor(val context: Context) : ListDataSource {
    private var networkController: NetworkController = NetworkController(context)

    override fun getListDataFromServer(
        sf: String,
        liveDataList: MutableLiveData<MutableList<DictionaryMeaning>>,
        failureCallback: IFailureCallback
    ) {
        if (NetworkUtils.isConnectedToInternet(context)) {
            networkController.getListData(sf, object : NetworkResponseCallback {
                override fun onSuccessResponse(jsonData: String, responseCode: Int) {
                    try {
                        val gson = Gson()
                        val listType = object : TypeToken<List<DictionaryMeaning>>() {}.type
                        val output: ArrayList<DictionaryMeaning> = gson.fromJson(jsonData, listType)
                        liveDataList.value = output
                        if (output.isEmpty())
                            failureCallback.onError("No Data matching the criteria found")
                    } catch (ex: JsonParseException) {
                        failureCallback.onError("No Data matching the criteria found")
                    }
                }

                override fun onError(errorCode: Int, errorData: String) {
                    failureCallback.onError(errorData)
                }
            })
        } else {
            failureCallback.onError("Please check internet connection")
        }

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: ListDataRemoteRepository? = null

        fun getInstance(context: Context): ListDataRemoteRepository? {
            if (instance == null) {
                instance = ListDataRemoteRepository(context)
            }
            return instance
        }


    }


}
