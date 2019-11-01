package com.example.androidacademymovies.ListMovies
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.movies_activity.*
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidacademymovies.API.APIService
import com.example.androidacademymovies.API.Entities.ConfigurationEntity
import com.example.androidacademymovies.API.Entities.Movie
import com.example.androidacademymovies.API.MoviesService
import com.example.androidacademymovies.CoroutineExample.CoroutineActivity
import com.example.androidacademymovies.DetailMovie.DetailMovieGallery
import com.example.androidacademymovies.R
import com.example.androidacademymovies.ThreadExample.ThreadActivity


class MainActivity : AppCompatActivity() {

    private var items: List<Movie> = listOf()
    private var viewModel = MoviesListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_activity)
        initViewModels()
        getMovies()
    }

    private fun getMovies() {
        val apiService = APIService()
        apiService.getMovies(viewModel)
    }

    private fun initViewModels() {
        viewModel = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)

        viewModel.getMovieResponse().observe(this, object : Observer<Pair<List<Movie>, ConfigurationEntity?>> {
            override fun onChanged(moviesAndConfig: Pair<List<Movie>, ConfigurationEntity?>) {

                val config = moviesAndConfig.second ?: return
                MoviesService.baseImageUrl = config.secure_base_url
                MoviesService.backdrop_size = config.backdrop_sizes[2]
                MoviesService.poster_size = config.poster_sizes[4]

                items = moviesAndConfig.first
                val movieAdapter = MoviesAdapter(
                    items,
                    { position: Int -> movieItemClicked(position) })
                movieRecyclerView.adapter = movieAdapter

            }
        })

        viewModel.getLoading().observe(this, object : Observer<Boolean> {
            override fun onChanged(showLoading: Boolean) {
                if(showLoading) llProgressBar.visibility = View.VISIBLE
                else llProgressBar.visibility = View.GONE
            }
        })
    }


    private fun movieItemClicked(position: Int) {
        val movies: ArrayList<Movie> = ArrayList<Movie>(items)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_screen_slide, DetailMovieGallery.newInstance(position, movies))
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.coroutines -> {
            startNewActivity("coroutines")
            true
        }
        R.id.threads -> {
            startNewActivity("threads")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun startNewActivity(nameActivity: String) {
        if (nameActivity == "coroutines") {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)

        } else {
            val intent = Intent(this, ThreadActivity::class.java)
            startActivity(intent)

        }
    }
}
