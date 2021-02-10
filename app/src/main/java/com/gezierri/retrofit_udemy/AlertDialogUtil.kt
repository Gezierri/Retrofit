package com.gezierri.retrofit_udemy

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.gezierri.retrofit_udemy.model.Produto
import kotlinx.android.synthetic.main.form_product.view.*

class AlertDialogUtil(private val context: Context, private val viewGroup: ViewGroup) {

    fun show(created: (createdProduct: Produto) -> Unit) {
        val createdView = LayoutInflater.from(context).inflate(
            R.layout.form_product,
            viewGroup,
            false
        )

        androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("Adicionar Produto")
            .setView(createdView)
            .setPositiveButton("salvar") { _, _ ->
                val name = createdView.form_product_title.text.toString()
                val description = createdView.form_product_description.text.toString()
                val price: Float = if (createdView.form_product_price.text.isNullOrEmpty()) {
                    0F
                } else {
                    createdView.form_product_price.text.toString().toFloat()
                }

                val url = createdView.form_product_url.text.toString()

                val product = Produto("", name, description, price, url)
                ProdutoWebClient().insert(product, object : ProdutoResponse<Produto> {
                    override fun success(response: Produto) {
                        created(response)
                    }

                })
            }
            .show()
    }

    fun alter(product: Produto, altered: (alteredProduct: Produto) -> Unit) {
        val createdView = LayoutInflater.from(context).inflate(
            R.layout.form_product,
            viewGroup,
            false
        )

        createdView.form_product_title.setText(product.titulo)
        createdView.form_product_description.setText(product.descricao)
        createdView.form_product_url.setText(product.imagem)

        androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("Alterar Produto")
            .setView(createdView)
            .setPositiveButton("salvar") { _, _ ->
                val name = createdView.form_product_title.text.toString()
                val description = createdView.form_product_description.text.toString()
                val url = createdView.form_product_url.text.toString()
                val alteredProduct =
                    product.copy(titulo = name, descricao = description, imagem = url)
                ProdutoWebClient().update(alteredProduct) { response, throwable ->
                    response?.let {
                        altered(it)
                    }
                    throwable?.let {
                        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }
}