package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RetailPriceX(
    @SerializedName("amount") val amount: Double?,
    @SerializedName("currencyCode") val currencyCode: String?
)