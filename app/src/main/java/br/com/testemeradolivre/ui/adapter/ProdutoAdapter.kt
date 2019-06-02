package br.com.testemeradolivre.ui.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import br.com.testemeradolivre.R
import br.com.testemeradolivre.model.Results
import br.com.testemeradolivre.util.Utils
import kotlinx.android.synthetic.main.card_view_resultado.view.*
import java.net.URL
import java.text.NumberFormat
import java.util.ArrayList

class ProdutoAdapter (private val context: Context, private var resultsList: MutableList<Results>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<ProdutoAdapter.PessoaViewHolder>() {

    private var data = resultsList

    interface OnItemClickListener {
        fun onClickProduto(item: Results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_resultado, parent, false)
        return PessoaViewHolder(view)
    }

    override fun getItemCount() = resultsList.size

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bindView(resultsList[position])
    }

    inner class PessoaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tv_descricao = itemView.tv_descricao
        val tv_preco = itemView.tv_preco
        val tv_parcela = itemView.tv_parcela
        val iv_produto = itemView.iv_produto

        fun bindView(results : Results) {
            val defaultFormat = NumberFormat.getInstance(Utils.getCurrentLocale(itemView.context))

            tv_descricao.text = results.title
            tv_preco.text = "R$ ${defaultFormat.format(results.price)}"
            tv_parcela.text = "${results.installments!!.quantity}x ${defaultFormat.format(results.installments!!.amount)}"

            DownLoadImageTask(iv_produto).execute(results.thumbnail)

        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            listener.onClickProduto(data[adapterPosition])
        }

    }

    private class DownLoadImageTask(internal val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val urlOfImage = urls[0]
            return try {
                val inputStream = URL(urlOfImage).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) { // Catch the download exception
                e.printStackTrace()
                null
            }
        }
        override fun onPostExecute(result: Bitmap?) {
            if(result!=null){
                imageView.setImageBitmap(result)
            }
        }
    }
}