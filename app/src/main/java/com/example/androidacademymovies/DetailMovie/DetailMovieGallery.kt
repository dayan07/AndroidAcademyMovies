package com.example.androidacademymovies.DetailMovie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.androidacademymovies.API.Entities.Movie

import com.example.androidacademymovies.R


/**
 * A simple [Fragment] subclass.
 */
private const val POSITION_KEY = "position"
private const val MOVIES_KEY = "movies"

class DetailMovieGallery : Fragment() {

    private val position get() = arguments?.getInt(POSITION_KEY, 0) ?: 0
    private val movies get() = arguments?.getParcelableArrayList<Movie>(MOVIES_KEY) ?: ArrayList<Movie>()


    companion object {

        fun newInstance(position: Int, movies: ArrayList<Movie>): DetailMovieGallery {
            val fragment = DetailMovieGallery()
            val args = Bundle()
            args.putInt(POSITION_KEY, position)
            args.putParcelableArrayList(MOVIES_KEY, movies)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_movie_gallery, container, false)
        view.findViewById<ViewPager>(R.id.detail_movie_gallery).run{
            adapter = MovieScreenSlidePagerAdapter(requireFragmentManager() )
            currentItem = position
        }
        return view
    }



    private inner class MovieScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
        override fun getCount(): Int = movies.size

        override fun getItem(position: Int): Fragment {
            return DetailMovie.newInstance(movies[position])
        }
    }

}
