package br.com.testemeradolivre.model

import java.io.Serializable

class Retorno : Serializable {

    var statusCode: Int = 0
    var strRetorno: String? = null
    var mensagem: String? = null
}
