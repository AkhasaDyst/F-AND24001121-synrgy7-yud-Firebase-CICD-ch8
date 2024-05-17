package com.yudhi.moviedatabase.model.movie


import com.google.gson.annotations.SerializedName

data class Responses(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)