package com.example.androidacademymovies.ListMovies
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidacademymovies.API.Entities.*


class MoviesListViewModel: ViewModel() {
    private var pbVisibility = MutableLiveData<Boolean>()
    private var movieResponse = MutableLiveData<Pair<List<Movie>, ConfigurationEntity?>>()

    fun getMovieResponse(): MutableLiveData<Pair<List<Movie>, ConfigurationEntity?>>{
        return movieResponse
    }

    fun updateMovies(moviesAndConfig: Pair<List<Movie>, ConfigurationEntity?>){
        movieResponse.postValue(moviesAndConfig)
    }

    fun showLoading() {
        pbVisibility.postValue(true)
    }

    fun hideLoading() {
        pbVisibility.postValue(false)
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return pbVisibility
    }
}