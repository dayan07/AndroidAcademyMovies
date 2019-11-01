package com.example.androidacademymovies.CoroutineExample


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidacademymovies.R
import kotlinx.android.synthetic.main.fragment_counter.*
import kotlinx.android.synthetic.main.fragment_counter.view.*


/**
 * A simple [Fragment] subclass.
 */
class CounterFragment : Fragment() {


    companion object {

        fun newInstance(): CounterFragment {
            return CounterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)

        val viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)
        viewModel.getProgressState().observe(this, object : Observer<String> {
            override fun onChanged(newString: String?) {
                tv_coroutinesInfo.setText(newString)
            }
        })

        val counterTask: CounterCoroutinesTask? =
            CounterCoroutinesTask()
        view.btn_create?.setOnClickListener {
            counterTask?.createCoroutine(viewModel)        }
        view.btn_start?.setOnClickListener {
            counterTask?.startCoroutine()
        }
        view.btn_stop?.setOnClickListener {
            counterTask?.stopCoroutine()

        }


        return view

    }
}
