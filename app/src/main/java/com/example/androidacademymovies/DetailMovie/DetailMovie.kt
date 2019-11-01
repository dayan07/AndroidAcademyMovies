package com.example.androidacademymovies.DetailMovie
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.androidacademymovies.API.Entities.Movie
import com.example.androidacademymovies.API.MoviesService
import com.example.androidacademymovies.API.Entities.VideoEntity
import com.example.androidacademymovies.API.APIService
import com.example.androidacademymovies.R
import kotlinx.android.synthetic.main.detail_movie.view.*


private const val MOVIE_KEY = "movie"

class DetailMovie: Fragment() {

    private var viewModel = DetailViewModel()
    private val movie get() = arguments?.getParcelable<Movie>(MOVIE_KEY)

    var linkTrailer: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_movie, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        getVideos()
    }

    private fun getVideos() {
        if (movie == null) return
        val apiService = APIService()
        apiService.getVideosByMovieId(movie!!.id, viewModel)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        viewModel.getVideosResponse().observe(this, object : Observer<List<VideoEntity>> {
            override fun onChanged(videos: List<VideoEntity>) {
                linkTrailer = MoviesService.youtubeUrl + videos[0].videoKey
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView(view)
    }

    private fun initializeView(view: View) {
        if (movie == null) return
        view.btn_show_trailer.setOnClickListener{
            val webIntent: Intent = Uri.parse(linkTrailer).let { webpage ->
                Intent(Intent.ACTION_VIEW, webpage)
            }
            startActivity(webIntent)

        }

        view.iv_main.load(MoviesService.baseImageUrl + MoviesService.backdrop_size + movie!!.mainImage) {
            crossfade(true)
        }
        view.iv_second.load(MoviesService.baseImageUrl + MoviesService.poster_size + movie!!.image) {
            crossfade(true)
        }
        view.tv_name.text = movie!!.name
        view.tv_when_released.text = movie!!.whenReleased
        view.tv_description.text = movie!!.description
    }

    companion object {
        fun newInstance(movie: Movie): DetailMovie {
            val fragment = DetailMovie()
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)
            fragment.arguments = args
            return fragment
        }
    }


}
