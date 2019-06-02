package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Seller : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("power_seller_status")
    @Expose
    var power_seller_status: String? = null

    @SerializedName("car_dealer")
    @Expose
    var car_dealer: Boolean? = null

    @SerializedName("real_estate_agency")
    @Expose
    var real_estate_agency: Boolean? = null
}