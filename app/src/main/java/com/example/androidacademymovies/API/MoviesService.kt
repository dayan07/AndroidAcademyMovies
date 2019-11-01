package com.example.androidacademymovies.API
import com.example.androidacademymovies.API.Entities.ConfigurationResponse
import com.example.androidacademymovies.API.Entities.MovieResponse
import com.example.androidacademymovies.API.Entities.VideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    fun getPopularMoviesData(@Query("api_key") api_key: String): Call<MovieResponse>

    @GET("configuration")
    fun getConfiguration(@Query("api_key") api_key: String): Call<ConfigurationResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movie_id: Int, @Query("api_key") api_key: String): Call<VideoResponse>

    companion object {

        var baseUrl = "https://api.themoviedb.org/3/"
        var api_key = "YOUR_API_KEY"
        var youtubeUrl = "https://www.youtube.com/watch?v="
        var baseImageUrl = ""
        var backdrop_size = ""
        var poster_size = ""


    }

}

