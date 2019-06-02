package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Categoria : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("descricao")
    @Expose
    var descricao: String? = null

    @SerializedName("urlImagem")
    @Expose
    var urlImagem: String? = null
}
