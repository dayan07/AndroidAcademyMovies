package com.example.androidacademymovies.API

import com.example.androidacademymovies.API.Entities.ConfigurationResponse
import com.example.androidacademymovies.API.Entities.MovieResponse
import com.example.androidacademymovies.API.Entities.VideoResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private fun buildRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(MoviesService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


    private fun getService(): MoviesService {
        return buildRetrofit().create(MoviesService::class.java)
    }

    fun getCallPopularMoviesData(): Call<MovieResponse> {
        return getService().getPopularMoviesData(MoviesService.api_key)
    }

    fun getCallConfigData(): Call<ConfigurationResponse> {
        return getService().getConfiguration(MoviesService.api_key)
    }

    fun getCallVideosData(movieId: Int): Call<VideoResponse> {
        return getService().getVideos(movieId, MoviesService.api_key)
    }


}