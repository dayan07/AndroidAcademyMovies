package com.example.androidacademymovies.CoroutineExample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidacademymovies.R

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment,
                    CounterFragment.newInstance())
                .commit()
        }

    }




}
