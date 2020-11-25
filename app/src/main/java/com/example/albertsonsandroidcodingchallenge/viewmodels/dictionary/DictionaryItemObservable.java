package com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.albertsonsandroidcodingchallenge.model.LF;
import com.example.albertsonsandroidcodingchallenge.model.Var;

public class DictionaryItemObservable extends BaseObservable {
    private LF dictionary;

    public DictionaryItemObservable(LF dictionary) {
        this.dictionary = dictionary;
    }

    public void setUp() {

    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }


    @Bindable
    public String getFrequency() {
        return String.valueOf(dictionary.getFreq());
    }


    @Bindable
    public String getLF() {
        return dictionary.getLf();
    }

    public String getSince() {
        return String.valueOf(dictionary.getSince());
    }

    public String getVars() {
        if (dictionary.getVars() != null && !dictionary.getVars().isEmpty()) {
            StringBuilder text = new StringBuilder("Other words:");
            for (Var var : dictionary.getVars()) {
                text.append(",").append(var.getLf());
            }
            return text.toString();

        }
        return "";
    }

}
