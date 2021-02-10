package com.gezierri.retrofit_udemy

import android.util.Log
import com.gezierri.retrofit_udemy.api.RetrofitConfig
import com.gezierri.retrofit_udemy.model.Produto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoWebClient {

    fun list(produtoResponse: ProdutoResponse<ArrayList<Produto>>){
        val call = RetrofitConfig().produtoService().listProdutos()
        call.enqueue(object : retrofit2.Callback<ArrayList<Produto>>{
            override fun onResponse(
                call: Call<ArrayList<Produto>>,
                response: Response<ArrayList<Produto>>
            ) {
                response.body()?.let {
                    produtoResponse.success(it)
                }
            }

            override fun onFailure(call: Call<ArrayList<Produto>>, t: Throwable) {
                Log.i("ERROR", t.toString())
            }

        })
    }

    fun insert(produto: Produto, produtoResponse: ProdutoResponse<Produto>){
        val call = RetrofitConfig().produtoService().insert(produto)
        call.enqueue(object : Callback<Produto> {
            override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                 response.body()?.let {
                     produtoResponse.success(it)
                 }
            }

            override fun onFailure(call: Call<Produto>, t: Throwable) {
                Log.i("ERROR", t.toString())
            }

        })
    }

    fun update(produto: Produto, callResponse:(produtos: Produto?, throwable: Throwable?)->Unit){
        val call = produto.id?.let { RetrofitConfig().produtoService().update(produto, it) }
        call?.enqueue(object : Callback<Produto> {
            override fun onResponse(call: Call<Produto>, response: Response<Produto>) {
                callResponse(response.body(), null)
            }

            override fun onFailure(call: Call<Produto>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun delete(id: String, callResponse: (response: String, throwable: Throwable?) -> Unit){
        val call = RetrofitConfig().produtoService().delete(id)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let {
                    callResponse(it.toString(), null)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}