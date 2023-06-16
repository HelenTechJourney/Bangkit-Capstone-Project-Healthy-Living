package com.example.healthyliving.remote.retrofit

import com.example.healthyliving.remote.response.*
import okhttp3.MultipartBody
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
    ): Call<ResponseMessage>

    @GET("user_data_form2")
    fun getResult(
        @Header("Authorization") token: String
    ): Call<ResultResponse>
    @Multipart
    @POST("update_profil")
    fun editProfile(
        @Header("Authorization") token: String,
        @Field("nama") name: String,
        @Part gambar: MultipartBody.Part
    ): Call<ResponseMessage>

 @GET("artikel")
    suspend fun getArticle(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ArticleResponse

    @GET("resep")
    fun getRecipe(
        @Header("Authorization") token: String
    ): Call<RecipeResponse>
}