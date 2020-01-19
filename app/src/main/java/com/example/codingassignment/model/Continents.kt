package com.example.codingassignment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Continents(
    @SerializedName("name") val name: String,
    @SerializedName("callingCodes") val callingCodes: List<String>,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
    @SerializedName("subregion") val subregion: String,
    @SerializedName("population") val population: Int,
    @SerializedName("borders") val borders: List<String>,
    @SerializedName("nativeName") val nativeName: String,
    @SerializedName("numericCode") val numericCode: Int,
    @SerializedName("flag") val flag: String
) : Serializable