package com.gezierri.retrofit_udemy

import com.gezierri.retrofit_udemy.model.Produto

interface ProdutoResponse<T> {

    fun success(response: T)
}
