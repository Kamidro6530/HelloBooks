package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PanelizationSummary(
    @SerializedName("containsEpubBubbles") val containsEpubBubbles: Boolean?,
    @SerializedName("containsImageBubbles") val containsImageBubbles: Boolean?
)