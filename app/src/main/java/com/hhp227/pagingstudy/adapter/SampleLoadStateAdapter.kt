package com.hhp227.pagingstudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hhp227.pagingstudy.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_load_state.view.*

class SampleLoadStateAdapter : LoadStateAdapter<SampleLoadStateAdapter.LoadStateViewHolder>() {
    private lateinit var onRetryListener: () -> Unit

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false))
    }

    inner class LoadStateViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            containerView.retry_button.setOnClickListener { onRetryListener() }
        }

        fun bind(loadState: LoadState) = with(containerView) {
            progress_bar.isVisible = loadState is LoadState.Loading
            retry_button.isVisible = loadState is LoadState.Error
            error_msg.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                error_msg.text = loadState.error.localizedMessage
            }
        }
    }
}