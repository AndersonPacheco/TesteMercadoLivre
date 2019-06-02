package br.com.testemeradolivre.data

import android.content.Context
import br.com.testemeradolivre.model.Retorno
import br.com.testemeradolivre.util.ConnectionDetector
import br.com.testemeradolivre.util.Constantes
import org.json.JSONObject
import org.json.JSONTokener
import java.io.*
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class Servico {

    fun verificarConexao(context: Context): Boolean {
        val cd = ConnectionDetector(context)
        return cd.isConnectingToInternet
    }

    @Throws(SocketTimeoutException::class, IOException::class, Exception::class)
    fun request(urlString: String, method: String): Retorno {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        val retorno = Retorno()

        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection

            connection.readTimeout = Constantes.READ_TIMEOUT
            connection.connectTimeout = Constantes.CONNECT_TIMEOUT
            connection.requestMethod = method
            connection.setRequestProperty(Constantes.WS_ACCEPT, Constantes.WS_APPLICATION_JSON)
//            connection.setRequestProperty(Constantes.WS_CONTENT_TYPE, Constantes.WS_APPLICATION_JSON)
            connection.doInput = true

            connection.connect()

            if (connection.responseCode == HttpURLConnection.HTTP_CREATED
                || connection.responseCode == HttpURLConnection.HTTP_ACCEPTED
                || connection.responseCode == HttpURLConnection.HTTP_OK
            ) {
                inputStream = connection.inputStream
                val message = JSONTokener(convertStreamToStringRequest(inputStream)).nextValue() as JSONObject
                retorno.statusCode = connection.responseCode
                retorno.strRetorno = message.toString()
                retorno.mensagem = connection.responseMessage
            } else if (connection.responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = connection.errorStream
                val message = JSONTokener(convertStreamToStringRequest(inputStream)).nextValue() as JSONObject
                retorno.statusCode = connection.responseCode
                retorno.mensagem = if (message.isNull("message")) "" else message.get("message").toString()
            } else {
                inputStream = connection.errorStream
                val message = JSONTokener(convertStreamToStringRequest(inputStream)).nextValue() as JSONObject
                retorno.statusCode = connection.responseCode
                retorno.mensagem = if (message.isNull("message")) "" else message.get("message").toString()
            }
            return retorno
        } finally {
            inputStream!!.close()
            connection!!.disconnect()
        }
    }

    fun convertStreamToStringRequest(inpustStream: InputStream):String {
        if (inpustStream != null) {
            val writer: Writer = StringWriter()
            val sb = StringBuilder()
            var line: String?

            val br = BufferedReader(InputStreamReader(inpustStream, "UTF-8"))
            line = br.readLine()

            while (line != null) {
                sb.append(line)
                line = br.readLine()
                writer.write(line)
            }
            br.close()
            return sb.toString()
        } else {
            return ""
        }
    }
}
