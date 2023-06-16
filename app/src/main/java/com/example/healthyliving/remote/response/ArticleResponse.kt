package com.example.healthyliving.remote.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticleResponse(

	@field:SerializedName("data")
	val data: List<ArtikelItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
@Entity(tableName = "article")
data class ArtikelItem(
	@PrimaryKey
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("referensi")
	val referensi: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("gambar")
	val gambar: String
): Parcelable