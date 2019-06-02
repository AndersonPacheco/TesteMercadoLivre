package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Results : Serializable {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("site_id")
    @Expose
    var site_id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("seller")
    @Expose
    var seller: Seller? = null

    @SerializedName("price")
    @Expose
    var price: Int? = 0

    @SerializedName("currency_id")
    @Expose
    var currency_id: String? = null

    @SerializedName("available_quantity")
    @Expose
    var available_quantity: Int? = 0

    @SerializedName("sold_quantity")
    @Expose
    var sold_quantity: Int? = 0

    @SerializedName("buying_mode")
    @Expose
    var buying_mode: String? = null

    @SerializedName("listing_type_id")
    @Expose
    var listing_type_id: String? = null

    @SerializedName("stop_time")
    @Expose
    var stop_time: String? = null

    @SerializedName("condition")
    @Expose
    var condition: String? = null

    @SerializedName("permalink")
    @Expose
    var permalink: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    @SerializedName("accepts_mercadopago")
    @Expose
    var accepts_mercadopago: Boolean? = null

    @SerializedName("installments")
    @Expose
    var installments: Installments? = null
}