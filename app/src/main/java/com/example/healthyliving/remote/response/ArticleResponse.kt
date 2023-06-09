package com.example.healthyliving.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticleResponse(

	@field:SerializedName("data")
	val data: List<ArtikelItem>,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class ArtikelItem(
	@field:SerializedName("referensi")
	val referensi: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("gambar")
	val gambar: String
): Parcelable