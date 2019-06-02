package br.com.testemeradolivre.data

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import br.com.testemeradolivre.model.*
import br.com.testemeradolivre.util.Constantes
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.util.ArrayList

class ProdutoTask(private val delegate: ProdutoDelegate, private val context: Context) : AsyncTask<String, Produto, ProdutoRes>() {

    private var dialog: ProgressDialog? = null
    private val helper: Servico

    init {
        this.helper = Servico()
    }

    override fun onPreExecute() {
        super.onPreExecute()

        this.dialog = ProgressDialog(context)
        this.dialog!!.setMessage("Carregando")
        this.dialog!!.setCancelable(false)
        this.dialog!!.show()
    }


    override fun doInBackground(vararg params: String): ProdutoRes {
        val produtoRes = ProdutoRes()
        val param = params[0]
        val metodo = params[1]

        try {
//            val client = OkHttpClient()
//
//            val request = Request.Builder()
//                .url("https://api.mercadolibre.com/sites/MLB/search?q=$param")
//                .get()
//                .addHeader("User-Agent", "PostmanRuntime/7.11.0")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "760741e7-ef88-4eb8-819f-37eb66281180,a91cd8ad-98a8-41a4-b975-ccc41c39857f")
//                .addHeader("Host", "api.mercadolibre.com")
//                .addHeader("accept-encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("cache-control", "no-cache")
//                .build()
//
//            val response = client.newCall(request).execute()
//
//            val teste = response.message()




            val retorno = helper.request(Constantes.URL_SEARCH + param, metodo)
            if (retorno != null) {
                if (retorno!!.statusCode === HttpURLConnection.HTTP_OK) {

                    val json = JSONTokener(retorno!!.strRetorno).nextValue() as JSONObject
                    val produto = Produto()
                    produto.site_id = json.getString("site_id")
                    produto.query = json.getString("query")
                    val paging = Paging()
                    paging.total = json.getJSONObject("paging").getInt("total")
                    paging.offset = json.getJSONObject("paging").getInt("offset")
                    paging.limit = json.getJSONObject("paging").getInt("limit")
                    paging.primary_results = json.getJSONObject("paging").getInt("primary_results")
                    produto.paging = paging

                    val jsonArray = json.getJSONArray("results")

                    val resultList = ArrayList<Results>()
                    for (i in 0 until jsonArray.length()) {
                        val results = Results()
                        results.id = jsonArray.getJSONObject(i).getString("id")
                        results.site_id = jsonArray.getJSONObject(i).getString("site_id")
                        results.title = jsonArray.getJSONObject(i).getString("title")
                        val seller = Seller()
                        seller.id = jsonArray.getJSONObject(i).getJSONObject("seller").getInt("id")
                        seller.power_seller_status = jsonArray.getJSONObject(i).getJSONObject("seller").getString("power_seller_status")
                        seller.car_dealer = jsonArray.getJSONObject(i).getJSONObject("seller").getBoolean("car_dealer")
                        seller.real_estate_agency = jsonArray.getJSONObject(i).getJSONObject("seller").getBoolean("real_estate_agency")
                        results.seller = seller
                        results.price = jsonArray.getJSONObject(i).getInt("price")
                        results.currency_id = jsonArray.getJSONObject(i).getString("currency_id")
                        results.available_quantity = jsonArray.getJSONObject(i).getInt("available_quantity")
                        results.sold_quantity = jsonArray.getJSONObject(i).getInt("sold_quantity")
                        results.buying_mode = jsonArray.getJSONObject(i).getString("buying_mode")
                        results.listing_type_id = jsonArray.getJSONObject(i).getString("listing_type_id")
                        results.stop_time = jsonArray.getJSONObject(i).getString("stop_time")
                        results.condition = jsonArray.getJSONObject(i).getString("condition")
                        results.permalink = jsonArray.getJSONObject(i).getString("permalink")
                        results.thumbnail = jsonArray.getJSONObject(i).getString("thumbnail")
                        results.accepts_mercadopago = jsonArray.getJSONObject(i).getBoolean("accepts_mercadopago")
                        val installments = Installments()
                        installments.quantity = jsonArray.getJSONObject(i).getJSONObject("installments").getInt("quantity")
                        installments.amount =  jsonArray.getJSONObject(i).getJSONObject("installments").getDouble("amount")
                        installments.rate = jsonArray.getJSONObject(i).getJSONObject("installments").getInt("rate")
                        installments.currency_id = jsonArray.getJSONObject(i).getJSONObject("installments").getString("currency_id")
                        results.installments = installments

                        resultList.add(results)
                    }
                    produto.results = resultList

                    produtoRes.produto = produto
                    produtoRes.statusCode = retorno!!.statusCode
                    produtoRes.mensagem = retorno!!.mensagem
                } else {
                    produtoRes.statusCode = retorno!!.statusCode
                    produtoRes.mensagem = retorno!!.mensagem
                }
            } else {
                produtoRes.statusCode = 400
                produtoRes.mensagem = Constantes.MSG_PROBLEMA_PESQUISA_IO
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return produtoRes
    }

    override fun onPostExecute(result: ProdutoRes) {
        super.onPostExecute(result)
        delegate.onProdutoResult(result)
        this.dialog!!.dismiss()
    }
}
