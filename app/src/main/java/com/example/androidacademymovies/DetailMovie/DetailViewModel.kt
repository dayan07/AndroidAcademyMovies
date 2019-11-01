package com.example.androidacademymovies.DetailMovie
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidacademymovies.API.Entities.*


class DetailViewModel: ViewModel() {

    private var videosResponse = MutableLiveData<List<VideoEntity>>()

    fun getVideosResponse(): MutableLiveData<List<VideoEntity>> {
        return videosResponse
    }


    fun updateVideosByMovieId(videos: List<VideoEntity>) {
        videosResponse.postValue(videos)
    }
}