package com.wooyj.alicorn.data.model

import com.google.gson.annotations.SerializedName

data class ModelUser(
    @SerializedName("id") val id: String,
    @SerializedName("pw") var pw: String,
    @SerializedName("name") val name: String,
    @SerializedName("company") val company: String,
    @SerializedName("position") val position: String,
    @SerializedName("imgUrl") val imgUrl: String,
)