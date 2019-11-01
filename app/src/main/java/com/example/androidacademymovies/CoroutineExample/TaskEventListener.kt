package com.example.androidacademymovies.CoroutineExample

interface TaskEventListener {

    fun onPreExecute ()
    fun onPostExecute ()
    fun onProgressUpdate (progress:Int)

}