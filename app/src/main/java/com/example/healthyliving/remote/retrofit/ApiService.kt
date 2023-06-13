package com.example.healthyliving.remote.retrofit

import com.example.healthyliving.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("daftar")
    fun createAccount(
        @Body requestSignUp: RequestSignup
    ): Call<ResponseMessage>

    @POST("login")
    fun accessAccount(
        @Body requestLogin: RequestLogin
    ): Call<LoginResponse>
    @POST("user_data_form1")
    fun createData(
        @Body requestForm: RequestForm
    ): Call<ResponseCalculator>

    @GET("user_data_form2")
    fun getResult(
        @Header("Authorization") token: String
    ): Call<ResultResponse>
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
    @GET("artikel")
   fun getArticle(
        @Header("Authorization") token: String
    ): Call<ArticleResponse>

    @GET("resep")
    fun getRecipe(
        @Header("Authorization") token: String
    ): Call<RecipeResponse>

}