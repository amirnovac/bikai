package com.amir.bikai.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PrizeModel : Serializable  {
    @SerializedName("prizes")
    @Expose
    private var prizes: List<Prize>? = null

    fun getPrizes(): List<Prize>? {
        return prizes
    }

    fun setPrizes(prizes: List<Prize>) {
        this.prizes = prizes
    }

    inner class Prize : Serializable {

        @SerializedName("year")
        @Expose
        var year: String? = null
        @SerializedName("category")
        @Expose
        var category: String? = null
        @SerializedName("laureates")
        @Expose
        var laureates: List<Laureate>? = null
        @SerializedName("overallMotivation")
        @Expose
        var overallMotivation: String? = null

    }

    inner class Laureate :Serializable {

        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("firstname")
        @Expose
        var firstname: String? = null
        @SerializedName("surname")
        @Expose
        var surname: String? = null
        @SerializedName("motivation")
        @Expose
        var motivation: String? = null
        @SerializedName("share")
        @Expose
        var share: String? = null

    }
}