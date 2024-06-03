package com.yudhi.moviedatabase.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.api.ApiClient
import com.yudhi.moviedatabase.databinding.FragmentDetailBinding
import com.yudhi.moviedatabase.databinding.FragmentMovieBinding
import com.yudhi.moviedatabase.model.movie.MovieResponse
import com.yudhi.moviedatabase.model.movie.RatingRequest
import com.yudhi.moviedatabase.model.movie.Responses
import com.yudhi.moviedatabase.viewmodel.MovieViewModel
import com.yudhi.moviedatabase.helper.Status
import com.yudhi.moviedatabase.model.MovieAdapter
import com.yudhi.moviedatabase.model.movie.Result
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = requireArguments().getInt("movieId")
        viewModel.getDetailMovie(movieId)
        viewModel.detailMovie.observe(viewLifecycleOwner, Observer {
            val movies = it
            val title = movies?.title
            val vote = movies?.voteAverage
            val voteCount = movies?.voteCount
            val overview = movies?.overview
            val release = movies?.releaseDate.toString()
            val poster = movies?.posterPath
            binding.tvJuduldetail.text = title
            binding.tvVotedetail.text = vote.toString()
            binding.tvVoteCountdetail.text = voteCount.toString()
            binding.tvRingkasandetail.text = overview
            binding.tvReleasedetail.text = release
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${poster}").into(binding.ivPosterdetail)
        })




    }

}