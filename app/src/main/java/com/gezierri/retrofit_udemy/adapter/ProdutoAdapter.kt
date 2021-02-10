package com.gezierri.retrofit_udemy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gezierri.retrofit_udemy.ProdutoWebClient
import com.gezierri.retrofit_udemy.R
import com.gezierri.retrofit_udemy.model.Produto
import kotlinx.android.synthetic.main.form_product.view.*
import kotlinx.android.synthetic.main.row_layout.view.*

class ProdutoAdapter(
    private var produtos: ArrayList<Produto>,
    private val context: Context,
    private val onItemClickListener: (produto: Produto, position: Int) -> Unit
): RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = this.produtos[position]
        holder.bindView(produto, context)
        holder.itemView.imgView.setOnClickListener {
            onItemClickListener(produto, position)
        }

        holder.itemView.bt_excluir.setOnClickListener {
            produto.id?.let { it1 ->
                ProdutoWebClient().delete(it1) { response, throwable ->
                    response.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        delete(position)
                    }
                    throwable?.let {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(produto: Produto, context: Context) {
            Glide.with(context)
                .load(produto.imagem)
                .into(itemView.imgView)
            itemView.txtTitle.text = produto.titulo
            itemView.txtDescription.text = produto.descricao
        }
    }

    fun getList(): ArrayList<Produto>{
        return produtos
    }

    fun addAll(produtos: ArrayList<Produto>){
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

    fun add(produto: Produto) {
        this.produtos.add(produto)
        notifyDataSetChanged()
    }

    fun update(produto: Produto, position: Int){
        this.produtos[position] = produto
        notifyItemChanged(position)
    }

    private fun delete(position: Int){
        this.produtos.removeAt(position)
        notifyDataSetChanged()
    }
}