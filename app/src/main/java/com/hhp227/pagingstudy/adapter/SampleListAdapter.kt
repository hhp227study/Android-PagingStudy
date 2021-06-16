package com.hhp227.pagingstudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hhp227.pagingstudy.R
import com.hhp227.pagingstudy.model.SampleData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_sample.view.*

class SampleListAdapter : PagingDataAdapter<SampleData, SampleListAdapter.SampleViewHolder>(SampleDiffCallback()) {
    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false))
    }

    inner class SampleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(data: SampleData?) {
            containerView.title.text = data?.name
        }
    }
}

private class SampleDiffCallback : DiffUtil.ItemCallback<SampleData>() {
    override fun areItemsTheSame(oldItem: SampleData, newItem: SampleData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SampleData, newItem: SampleData): Boolean {
        return oldItem.id == newItem.id
    }

}