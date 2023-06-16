package com.example.healthyliving.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthyliving.databinding.ItemUserBinding
import com.example.healthyliving.remote.response.ArtikelItem

class ArticleAdapter(val context: Context) :
    PagingDataAdapter<ArtikelItem, ArticleAdapter.ViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
        }
    }
    class ViewHolder (private var binding: ItemUserBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArtikelItem) {
        binding.tvItemTitle.text = data.judul
        Glide.with(itemView.context)
        .load(data.gambar)
        .into(binding.imgItemAvatar)
        }
    }

    private var onItemClickCallback: OnItemClickCallback?=null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: ArtikelItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArtikelItem>() {
            override fun areItemsTheSame(
                oldItem: ArtikelItem,
                newItem: ArtikelItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArtikelItem,
                newItem: ArtikelItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}