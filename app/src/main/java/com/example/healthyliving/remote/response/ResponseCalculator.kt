package com.example.healthyliving.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseCalculator(

	@field:SerializedName("data")
	val kalkulator: List<Kalkulator>,

	@field:SerializedName("message")
	val message: String
)

data class Kalkulator(

	@field:SerializedName("usia")
	val usia: String,

	@field:SerializedName("berat_badan")
	val beratBadan: String,

	@field:SerializedName("tinggi_badan")
	val tinggiBadan: String,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@field:SerializedName("aktivitas_fisik")
	val aktivitasFisik: String
)
