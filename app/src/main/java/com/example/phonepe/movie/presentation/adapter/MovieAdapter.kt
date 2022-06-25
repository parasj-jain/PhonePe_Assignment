package com.example.phonepe.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonepe.databinding.ItemViewMovieBinding
import com.example.phonepe.movie.domain.model.Movie

class MovieAdapter(
    private val movies: MutableList<Movie>,
    private val movieClick: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemViewMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movieTitle.text = movies[position].title
        holder.binding.movieOverview.text = movies[position].overview
        Glide.with(holder.itemView.context).load(movies[position].backdrop_path).into(holder.binding.moviePosterImage)
        holder.itemView.setOnClickListener { movieClick(movies[position]) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateData(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: ItemViewMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

}