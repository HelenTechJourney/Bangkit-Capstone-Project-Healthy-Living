package com.example.healthyliving.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecipeResponse(

	@field:SerializedName("data")
	val data: List<ResepItem>,

	@field:SerializedName("message")
	val message: String
)
@Parcelize
data class ResepItem(

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
