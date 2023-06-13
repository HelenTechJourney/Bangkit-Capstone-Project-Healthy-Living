package com.example.healthyliving.remote.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(

	@field:SerializedName("data")
	val result: List<ResultForm>,

	@field:SerializedName("message")
	val message: String
)

data class ResultForm(

	@field:SerializedName("bmr")
	val bmr: String,

	@field:SerializedName("bmi")
	val bmi: String,

	@field:SerializedName("rekomendasi_berat_badan")
	val rekomendasiBeratBadan: Int
)
