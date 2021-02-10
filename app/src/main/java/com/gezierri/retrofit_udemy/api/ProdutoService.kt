package com.gezierri.retrofit_udemy.api

import com.gezierri.retrofit_udemy.model.Produto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProdutoService {

    @GET("products")
    fun listProdutos(): Call<ArrayList<Produto>>

    @POST("products")
    fun insert(@Body produto: Produto): Call<Produto>

    @PUT("products/{id}")
    fun update(@Body produto: Produto, @Path("id")id: String): Call<Produto>

    @DELETE("products/{id}")
    fun delete(@Path("id")id: String): Call<ResponseBody>
}