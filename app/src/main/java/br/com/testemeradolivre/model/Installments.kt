package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Installments : Serializable {

    @SerializedName("quantity")
    @Expose
    var quantity: Int? = 0

    @SerializedName("amount")
    @Expose
    var amount: Double? = 0.0

    @SerializedName("rate")
    @Expose
    var rate: Int? = 0

    @SerializedName("currency_id")
    @Expose
    var currency_id: String? = null
}