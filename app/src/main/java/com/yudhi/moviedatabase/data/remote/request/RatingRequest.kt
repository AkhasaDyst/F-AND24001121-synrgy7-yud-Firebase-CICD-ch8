package com.yudhi.moviedatabase.data.remote.request

import com.google.gson.annotations.SerializedName

data class RatingRequest(
    @SerializedName("value")
    val values12: Double
)

