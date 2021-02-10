package com.gezierri.retrofit_udemy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gezierri.retrofit_udemy.adapter.ProdutoAdapter
import com.gezierri.retrofit_udemy.api.RetrofitConfig
import com.gezierri.retrofit_udemy.model.Produto
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ProdutoAdapter
    val produtos: ArrayList<Produto> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureRecyclerView()

        ProdutoWebClient().list(object : ProdutoResponse<ArrayList<Produto>> {
            override fun success(response:  ArrayList<Produto>) {
                adapter.addAll(response)
            }
        })

        fab.setOnClickListener {
            AlertDialogUtil(this, window.decorView as ViewGroup).show {
                adapter.add(it)
            }
        }
    }

    private fun configureRecyclerView(){
        val recyclerView = recyclerView
        adapter = ProdutoAdapter(produtos, this){produto, position ->
            AlertDialogUtil(this, window.decorView as ViewGroup).alter(produto){
                adapter.update(it, position)
            }
        }

        recyclerView.adapter = adapter
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
}