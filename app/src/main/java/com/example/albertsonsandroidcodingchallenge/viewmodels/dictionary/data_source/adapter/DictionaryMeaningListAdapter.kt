package com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.data_source.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonsandroidcodingchallenge.R
import com.example.albertsonsandroidcodingchallenge.model.LF
import com.example.albertsonsandroidcodingchallenge.viewmodels.dictionary.DictionaryItemObservable

class DictionaryMeaningListAdapter : RecyclerView.Adapter<DictionaryMeaningListAdapter.ViewHolder>() {
    private val data: ArrayList<LF> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.dictionary_meaning_list_item_view,
            FrameLayout(parent.context), false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lf = data[position]
        holder.setViewModel(DictionaryItemObservable(lf))
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun updateData(data: List<LF>?) {
        this.data.clear()
        if (data == null || data.isEmpty()) {

        } else {
            this.data.addAll(data)
        }
        notifyDataSetChanged()
    }

    fun removeData() {
        this.data.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemViewNew: View) : RecyclerView.ViewHolder(itemViewNew) {
        var binding: DictionaryMeaningListItemViewBinding? = null

        init {
            bind()
        }

        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemViewNew)
            }
        }

        fun unbind() {
            if (binding != null) {
                binding!!.unbind() // Don't forget to unbind
            }
        }

        fun setViewModel(viewModel: DictionaryItemObservable) {
            if (binding != null) {
                binding!!.observable = viewModel
            }
        }
    }

    companion object {
        private val TAG = "DictionaryMeaningListAdapter"
    }
}
