package com.example.androidacademymovies.API.Entities

import com.google.gson.annotations.SerializedName

data class VideoEntity(
        @SerializedName("key")
        val videoKey: String
)
