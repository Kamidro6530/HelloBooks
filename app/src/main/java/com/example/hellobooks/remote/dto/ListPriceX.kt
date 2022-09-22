package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListPriceX(
    @SerializedName("amountInMicros") val amountInMicros: Int?,
    @SerializedName("currencyCode")  val currencyCode: String?
)