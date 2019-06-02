package br.com.testemeradolivre.ui.activity

import android.graphics.Paint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import br.com.testemeradolivre.R
import br.com.testemeradolivre.model.Produto
import br.com.testemeradolivre.model.Results
import br.com.testemeradolivre.util.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detalhe.*
import kotlinx.android.synthetic.main.content_detalhe_produto.*
import java.text.NumberFormat

class DetalheActivity : AppCompatActivity() {

    var results = Results()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        setToolbar()
        getParametro()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun getParametro(){
        val extras = intent.extras
        if (extras != null) {
            results = extras.getSerializable("PARAM_PRODUTO") as Results
            if (results != null){
                carregarProduto()
            }
        }
    }

    private fun carregarProduto(){
        if(results != null){
            Glide
                .with(this)
                .load(results.thumbnail)
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_launcher)
                .into(iv_produto)

            val  defaultFormat = NumberFormat.getInstance(Utils.getCurrentLocale(this))

            tv_descricao.text = results.title
            tv_preco.text = "R$ " + defaultFormat.format(results.price)
            tv_parcela.text = "${results.installments!!.quantity}x ${results.installments!!.amount}"

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
