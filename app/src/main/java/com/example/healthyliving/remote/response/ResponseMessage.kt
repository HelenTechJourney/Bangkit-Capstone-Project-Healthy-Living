package com.example.healthyliving.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseMessage(

	@field:SerializedName("resultDaftar")
	val resultDaftar: ResultDaftar? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultDaftar(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName(" token")
	val token: String? = null
)
