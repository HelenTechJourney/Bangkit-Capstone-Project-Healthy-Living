package com.example.healthyliving.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthyliving.R
import com.example.healthyliving.remote.response.ResepItem

class RecipeAdapter(private val listRecipe: List<ResepItem>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.img_item_avatar)
        var tvName: TextView = view.findViewById(R.id.tv_item_title)
    }

    private var onItemClickCallback: OnItemClickCallback?=null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listRecipe[position]
        holder.tvName.text = data.judul
        Glide.with(holder.itemView)
            .load(data.gambar)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listRecipe[holder.adapterPosition])
        }
    }
    override fun getItemCount() = listRecipe.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ResepItem)
    }
}