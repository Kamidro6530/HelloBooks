package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerializedName("finskyOfferType") val finskyOfferType: Int?,
    @SerializedName("listPrice") val listPrice: ListPriceX?,
    @SerializedName("retailPrice") val retailPrice: RetailPrice?
)