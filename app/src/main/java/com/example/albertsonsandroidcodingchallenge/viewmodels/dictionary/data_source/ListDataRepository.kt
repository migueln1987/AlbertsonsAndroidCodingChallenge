package com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.albertsonsandroidcodingchallenge.model.DictionaryMeaning
import com.example.albertsonsandroidcodingchallenge.utils.IFailureCallback

class ListDataRepository private constructor(mContext: Context) : ListDataSource {

    private val remoteDataSource: ListDataSource?

    init {
        remoteDataSource = ListDataRemoteRepository.getInstance(mContext)
    }

    override fun getListDataFromServer(sf: String?, liveDataList: MutableLiveData<MutableList<DictionaryMeaning>>, failureCallback: IFailureCallback?) {
        remoteDataSource!!.getListDataFromServer(sf, liveDataList, failureCallback)
    }

    companion object {
        @Volatile
        private var INSTANCE: ListDataRepository? = null

        fun getInstance(mContext: Context): ListDataRepository? {
            if (INSTANCE == null) {
                synchronized(ListDataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ListDataRepository(mContext)
                    }
                }
            }
            return INSTANCE
        }
    }
}
