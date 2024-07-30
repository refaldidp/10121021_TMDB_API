package com.movie.tmdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.movieTitle)
        private val overview: TextView = itemView.findViewById(R.id.movieOverview)
        private val poster: ImageView = itemView.findViewById(R.id.moviePoster)
        private val rating: RatingBar = itemView.findViewById(R.id.movieRating)

        fun bind(movie: Movie) {
            title.text = movie.title
            overview.text = movie.overview
            rating.rating = movie.vote_average.toFloat() / 2 // TMDB memberikan rating dari 10, jadi bagi dua untuk 5 bintang
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(poster)
        }
    }
}
