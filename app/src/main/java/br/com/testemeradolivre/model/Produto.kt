package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Produto : Serializable {

    @SerializedName("site_id")
    @Expose
    var site_id: String? = null

    @SerializedName("query")
    @Expose
    var query: String? = null

    @SerializedName("paging")
    @Expose
    var paging: Paging? = null

    @SerializedName("results")
    @Expose
    var results: ArrayList<Results>? = null

    @SerializedName("secondary_results")
    @Expose
    var secondary_results: Double = 0.toDouble()

    @SerializedName("related_results")
    @Expose
    var related_results: Double = 0.toDouble()

    @SerializedName("sort")
    @Expose
    var sort: Categoria? = null

//    available_sorts
//    filters
//    available_filters
}
