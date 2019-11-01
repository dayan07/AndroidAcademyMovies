package com.example.androidacademymovies.CoroutineExample
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


class CounterViewModel: ViewModel(),
    TaskEventListener {
    private val showProgress = MutableLiveData<String>()


    override fun onPreExecute() {
        showProgress.postValue("Coroutines Activity")

    }

    override fun onPostExecute() {
        showProgress.postValue("Done")
    }

    override fun onProgressUpdate(progress: Int) {
        showProgress.postValue(progress.toString())
    }

    fun getProgressState(): MutableLiveData<String> {
        return showProgress
    }



}