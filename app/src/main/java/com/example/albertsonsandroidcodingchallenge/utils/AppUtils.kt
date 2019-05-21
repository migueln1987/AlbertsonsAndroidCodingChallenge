package com.example.albertsonsandroidcodingchallenge.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object AppUtils {

    @BindingAdapter("app:customAdapter")
    @JvmStatic
    fun bind(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager= LinearLayoutManager(recyclerView.context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,DividerItemDecoration.HORIZONTAL))

    }
}