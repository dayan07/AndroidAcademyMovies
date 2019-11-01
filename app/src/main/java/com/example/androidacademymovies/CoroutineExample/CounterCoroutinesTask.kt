package com.example.androidacademymovies.CoroutineExample
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CounterCoroutinesTask: CoroutineScope  {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob()

    private var job: Job? = null

    fun createCoroutine(counterViewModel: CounterViewModel) {
        job = launch(Dispatchers.IO, start = CoroutineStart.LAZY) {
            for (i in 1..10) {
                    launch(Dispatchers.Main) {
                        counterViewModel.onProgressUpdate(i)
                    }
                delay(500)
            }
            launch (Dispatchers.Main) {
                counterViewModel.onPostExecute()
            }
        }
        counterViewModel.onPreExecute()
    }

    fun stopCoroutine (){
        job?.cancel()
    }

    fun startCoroutine (){
        job?.start()
    }


}