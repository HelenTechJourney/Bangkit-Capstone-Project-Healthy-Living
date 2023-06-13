package com.example.healthyliving.ui.form

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.healthyliving.R
import com.example.healthyliving.remote.response.ArtikelItem

class RecomendAdapter(private val listArticle: List<ArtikelItem>) :
    RecyclerView.Adapter<RecomendAdapter.ViewHolder>() {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.img_item_avatar)
        var tvName: TextView = view.findViewById(R.id.tv_item_title)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_rec_recipe, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listArticle[position]
        holder.tvName.text = data.judul
        Glide.with(holder.itemView)
            .load(data.gambar)
            .into(holder.imageView)
    }
    override fun getItemCount() = listArticle.size
}