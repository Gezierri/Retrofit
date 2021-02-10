package com.gezierri.retrofit_udemy.model

import com.google.gson.annotations.SerializedName

data class Produto(

    @SerializedName("_id")
    val id: String?,

    @SerializedName("title")
    val titulo: String,

    @SerializedName("description")
    val descricao: String,

    @SerializedName("price")
    val preco: Float,

    @SerializedName("image")
    val imagem: String,
)