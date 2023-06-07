package com.example.healthyliving.remote.retrofit

import com.example.healthyliving.remote.response.LoginResponse
import com.example.healthyliving.remote.response.RequestLogin
import com.example.healthyliving.remote.response.RequestSignup
import com.example.storyapp.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("daftar")
    fun createAccount(
        @Body requestSignUp: RequestSignup
    ): Call<ResponseMessage>

    @POST("login")
    fun accessAccount(
        @Body requestLogin: RequestLogin
    ): Call<LoginResponse>

//    @Multipart
//    @POST("stories")
//    fun addNewStories(
//        @Header("Authorization") token: String,
//        @Part("description") description: RequestBody,
//        @Part photo: MultipartBody.Part,
//        @Part("lat") lat: Float?,
//        @Part("lon") lon: Float?
//    ): Call<ResponseMessage>

//    @GET("stories")
//    suspend fun getArticle(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): ListStoryResponse

//    @GET("stories")
//   fun getArticle(
//        @Header("Authorization") token: String
//    ): Call<ListStoryResponse>
//
//    @GET("stories/{id}")
//    fun getDetailArticle(
//        @Header("Authorization") token : String,
//        @Path("id") id: String
//    ): Call<DetailResponse>
}