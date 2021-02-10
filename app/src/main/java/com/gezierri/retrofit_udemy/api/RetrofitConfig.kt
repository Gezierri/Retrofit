package com.gezierri.retrofit_udemy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.176:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun produtoService(): ProdutoService{
        return retrofit.create(ProdutoService::class.java)
    }

}