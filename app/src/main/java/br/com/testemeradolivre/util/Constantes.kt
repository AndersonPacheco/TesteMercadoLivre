package br.com.testemeradolivre.util

object Constantes {

    val URL = "https://alodjinha.herokuapp.com/"
    val REQUEST_METHOD_GET = "GET"
    val REQUEST_METHOD_POST = "POST"
    val REQUEST_PROPERTY_AUTHORIZATION = "Authorization"
    val REQUEST_PROPERTY_CONTENT_LENGHT = "Content-Length"
    val READ_TIMEOUT = 10000
    val CONNECT_TIMEOUT = 15000

    val WS_CONTENT_TYPE = "Content-Type"
    val WS_APPLICATION_JSON = "application/json"
    val WS_ACCEPT = "Accept"
    val WS_CONTENT_TYPE_TEXT = "text/json"

    val MSG_PROBLEMA_PESQUISA_IO = "Desculpe, ocorreu um problema. Por favor, tente novamente mais tarde!"

    val URL_SEARCH = "https://api.mercadolibre.com/sites/MLB/search?q="//&category=%s"
    val URL_SEARCH_ID = "https://api.mercadolibre.com/items/%s"
    val CATEGORY_INFORMATICA = "MLA1648"
    val PRODUTO_ID = "produtoId"
}
