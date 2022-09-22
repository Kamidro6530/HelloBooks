package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class IndustryIdentifier(
    @SerializedName("identifier")val identifier: String?,
    @SerializedName("type")val type: String?
)