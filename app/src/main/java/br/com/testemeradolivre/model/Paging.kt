package br.com.testemeradolivre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Paging : Serializable {

    @SerializedName("total")
    @Expose
    var total: Int? = 0

    @SerializedName("offset")
    @Expose
    var offset: Int? = 0

    @SerializedName("limit")
    @Expose
    var limit: Int? = 0

    @SerializedName("primary_results")
    @Expose
    var primary_results: Int? = 0
}