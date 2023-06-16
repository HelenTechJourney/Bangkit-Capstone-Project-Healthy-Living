package com.example.healthyliving.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RecipeResponse(

	@field:SerializedName("data")
	val data: List<ResepItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
@Parcelize
data class ResepItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("referensi")
    val referensi: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("cara_membuats")
    val caraMembuats: List<CaraMembuatsItem>,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("gambar")
    val gambar: String,

    @field:SerializedName("bahans")
    val bahans: List<BahansItem>
): Parcelable
@Parcelize
data class BahansItem(
	@field:SerializedName("resep_id")
	val resepId: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String
): Parcelable
@Parcelize
data class CaraMembuatsItem(
	@field:SerializedName("resep_id")
	val resepId: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String
): Parcelable
