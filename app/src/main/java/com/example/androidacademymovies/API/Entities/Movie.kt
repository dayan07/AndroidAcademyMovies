package com.example.androidacademymovies.API.Entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
    data class Movie(
        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val name: String,

        @SerializedName("overview")
        var description: String,

        @SerializedName("poster_path")
        val image: String?,

        @SerializedName("backdrop_path")
        val mainImage: String?,

        @SerializedName("release_date")
        val whenReleased: String,

        @SerializedName("video")
        val isLinkTrailer: Boolean

    ): Parcelable


