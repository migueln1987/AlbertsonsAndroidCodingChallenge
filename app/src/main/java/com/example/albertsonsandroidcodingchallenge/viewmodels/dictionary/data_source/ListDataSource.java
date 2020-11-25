package com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source;

import androidx.lifecycle.MutableLiveData;

import com.example.albertsonsandroidcodingchallenge.model.DictionaryMeaning;
import com.example.albertsonsandroidcodingchallenge.utils.IFailureCallback;

import java.util.List;

public interface ListDataSource {

    default void getListDataFromServer(String sf, MutableLiveData<List<DictionaryMeaning>> liveDataList, IFailureCallback failureCallback) {
    }

    //repository methods

}
