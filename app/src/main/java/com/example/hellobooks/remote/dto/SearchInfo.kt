package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SearchInfo(
    @SerializedName("textSnippet") val textSnippet: String?
)