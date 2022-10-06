package com.example.hellobooks.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
  // @SerializedName("accessInfo")
  // val accessInfo: AccessInfo?,
    // @SerializedName("etag")
   //val etag: String?,
    //Item identifier required to  know whether the item has been added from the Api or not
   @SerializedName("id")
   val id: String?,
   //@SerializedName("kind")
  // val kind: String?,
  // @SerializedName("saleInfo")
  // val saleInfo: SaleInfo?,
  // @SerializedName("searchInfo")
  // val searchInfo: SearchInfo?,
  // @SerializedName("selfLink")
  // val selfLink: String?,
   @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo?
)