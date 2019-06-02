package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Response : Serializable {

    //@SerializedName("data") val data: @RawValue T? = null,

    @SerializedName("offset")
    @Expose
    var offset: Int = 0

    @SerializedName("total")
    @Expose
    var total: Int = 0

    @SerializedName("result")
    @Expose
    var result: String? = null

    var message: String? = null
}
