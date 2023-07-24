package io.invoicex.mob2con.network.service

import com.google.gson.annotations.SerializedName

data class SkuResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)
