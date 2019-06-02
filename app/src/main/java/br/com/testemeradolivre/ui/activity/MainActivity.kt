package br.com.testemeradolivre.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.testemeradolivre.R
import br.com.testemeradolivre.data.ProdutoDelegate
import br.com.testemeradolivre.data.ProdutoRes
import br.com.testemeradolivre.data.ProdutoTask
import br.com.testemeradolivre.model.Produto
import br.com.testemeradolivre.util.Constantes
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity(), ProdutoDelegate {

    var produto = Produto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton.setOnClickListener {
            doProduto(edtPesquisa.text.toString(), Constantes.REQUEST_METHOD_POST)
        }
    }

    private fun doProduto(param :String, metodo: String){
        ProdutoTask(this, this).execute(param, metodo)
    }

    override fun onProdutoResult(produtoRes: ProdutoRes) {
        if (produtoRes != null){
            if (produtoRes.statusCode == HttpURLConnection.HTTP_OK){
                produto = produtoRes.produto!!
                var intent = Intent(this, ResultadoActivity::class.java)
                intent.putExtra("PARAM_PRODUTO", produto)
                startActivity(intent)
            }
        }
    }
}
