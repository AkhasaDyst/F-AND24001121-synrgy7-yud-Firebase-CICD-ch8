package com.yudhi.moviedatabase.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yudhi.moviedatabase.R
import com.yudhi.domain.domain.model.ResultDomain
import com.yudhi.moviedatabase.presentation.ui.MovieFragmentDirections


class MovieAdapter(private var movies: List<ResultDomain>?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_judul)
        val overview: TextView = view.findViewById(R.id.tv_ringkasan)
        val poster: ImageView = view.findViewById(R.id.iv_poster)
        val vote: TextView = view.findViewById(R.id.tv_vote)
        val voteCount: TextView = view.findViewById(R.id.tv_voteCount)
        val release: TextView = view.findViewById(R.id.tv_release)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies?.get(position)
        val movieId = movie?.id!!.toInt()
        holder.title.text = movie?.title
        holder.overview.text = movie?.overview
        holder.vote.text = movie?.voteAverage.toString()
        holder.voteCount.text = movie?.voteCount.toString()
        holder.release.text = movie?.releaseDate.toString()
        // Use an image loading library like Glide or Picasso to load the poster image
        Glide.with(holder.poster.context)
            .load("https://image.tmdb.org/t/p/w500/${movie?.posterPath}").into(holder.poster)
        holder.itemView.setOnClickListener {
            val direction = MovieFragmentDirections.actionMovieFragmentToDetailFragment(movieId)
            holder.itemView.findNavController().navigate(direction)
        }
    }
    override fun getItemCount(): Int = movies?.size!!

    fun updateMovies(newMovies: List<ResultDomain>?) {
        movies = newMovies
        notifyDataSetChanged()
    }

}
