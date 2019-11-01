package com.example.androidacademymovies.API

import com.example.androidacademymovies.API.Entities.*
import com.example.androidacademymovies.DetailMovie.DetailViewModel
import com.example.androidacademymovies.ListMovies.MoviesListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import nl.komponents.kovenant.Kovenant
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.combine.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class APIService: CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    fun getVideosByMovieId(movie_id: Int, viewModel: DetailViewModel) {
        val call = RetrofitService().getCallVideosData(movie_id)

        call.enqueue(object : Callback<VideoResponse> {
            override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
                if (response.code() == 200) {
                    viewModel.updateVideosByMovieId(response.body()?.results ?: listOf())
                }
            }
            override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
            }
        })
    }

    fun getMovies(viewModel: MoviesListViewModel){
        viewModel.showLoading()
        combine(loadMovies(), getConfiguration()) success {
            launch(Dispatchers.Main) {
                viewModel.updateMovies(Pair(it.first, it.second))
                viewModel.hideLoading()
            }
        }
    }

    private fun loadMovies(): Promise<List<Movie>, Throwable> {
        val deferred = Kovenant.deferred<List<Movie>, Throwable>()
        val call = RetrofitService().getCallPopularMoviesData()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                deferred.resolve(response.body()?.results ?: listOf())
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                deferred.reject(t)
            }
        })

        return deferred.promise
    }

    private fun getConfiguration(): Promise<ConfigurationEntity?, Throwable> {
        val deferred = Kovenant.deferred<ConfigurationEntity?, Throwable>()
        val call = RetrofitService().getCallConfigData()
        call.enqueue(object : Callback<ConfigurationResponse> {
            override fun onResponse(call: Call<ConfigurationResponse>, response: Response<ConfigurationResponse>) {
                deferred.resolve(response.body()?.images)
            }

            override fun onFailure(call: Call<ConfigurationResponse>, t: Throwable) {
                deferred.reject(t)
            }
        })

        return deferred.promise
    }
}