package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReadingModes(
    @SerializedName("image")  val image: Boolean?,
    @SerializedName("text")   val text: Boolean?
)