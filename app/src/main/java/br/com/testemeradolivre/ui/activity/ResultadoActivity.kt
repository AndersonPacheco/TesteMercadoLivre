package br.com.testemeradolivre.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import br.com.testemeradolivre.R
import br.com.testemeradolivre.model.Produto
import br.com.testemeradolivre.model.Results
import br.com.testemeradolivre.ui.adapter.ProdutoAdapter
import kotlinx.android.synthetic.main.activity_resultado.*

class ResultadoActivity : AppCompatActivity(), ProdutoAdapter.OnItemClickListener {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ProdutoAdapter.PessoaViewHolder>? = null

    var produto = Produto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

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
            produto = extras.getSerializable("PARAM_PRODUTO") as Produto
            if (produto != null){
                carrega()
            }
        }
    }

    private fun carrega(){
        layoutManager = LinearLayoutManager(this)
        rv_produtos.layoutManager = layoutManager
        adapter = ProdutoAdapter(this,produto.results!!, this)
        rv_produtos.adapter = adapter
    }

    override fun onClickProduto(item: Results) {
        var intent = Intent(this, DetalheActivity::class.java)
        intent.putExtra("PARAM_PRODUTO", item)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
