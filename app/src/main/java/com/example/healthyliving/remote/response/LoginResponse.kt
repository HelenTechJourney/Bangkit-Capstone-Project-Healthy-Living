package com.example.healthyliving.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("resultLogin")
	val resultLogin: ResultLogin? = null,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String? = null
)

data class ResultLogin(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
