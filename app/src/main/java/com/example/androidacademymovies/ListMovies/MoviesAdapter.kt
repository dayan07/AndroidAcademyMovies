package com.example.androidacademymovies.ListMovies
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidacademymovies.API.Entities.Movie
import coil.api.load
import com.example.androidacademymovies.API.MoviesService
import com.example.androidacademymovies.R


class MoviesAdapter(var items: List<Movie>, val clickListener: (Int) -> Unit): RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    inner class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.tvTitle)
        private val description = itemView.findViewById<TextView>(R.id.tvDescription)
        private val image = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun bind(
            movie: Movie,
            clickListener: (Int) -> Unit
        ) {
            title.text = movie.name
            description.text = movie.description
            image.load(MoviesService.baseImageUrl + MoviesService.poster_size + movie.image) {
                crossfade(true)
            }

            itemView.setOnClickListener{ it: View? ->
                val position = adapterPosition
                (this@MoviesAdapter.clickListener)(position)

            }
        }

    }

}
