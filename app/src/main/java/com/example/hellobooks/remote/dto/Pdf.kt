package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Pdf(
    @SerializedName("acsTokenLink")  val acsTokenLink: String?,
    @SerializedName("downloadLink")  val downloadLink: String?,
    @SerializedName("isAvailable") val isAvailable: Boolean?
)