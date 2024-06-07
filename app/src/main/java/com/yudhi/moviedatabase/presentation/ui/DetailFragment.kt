package com.yudhi.moviedatabase.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yudhi.moviedatabase.common.Status
import com.yudhi.moviedatabase.databinding.FragmentDetailBinding
import com.yudhi.moviedatabase.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


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
        viewModel.getDetailMovie(movieId).observe(viewLifecycleOwner, Observer { resources ->
            when (resources.status) {
                Status.SUCCESS -> {
                    val movieDetail = resources.data
                    val title = movieDetail?.title
                    val vote = movieDetail?.voteAverage
                    val voteCount = movieDetail?.voteCount
                    val overview = movieDetail?.overview
                    val release = movieDetail?.releaseDate.toString()
                    val poster = movieDetail?.posterPath
                    binding.tvJuduldetail.text = title
                    binding.tvVotedetail.text = vote.toString()
                    binding.tvVoteCountdetail.text = voteCount.toString()
                    binding.tvRingkasandetail.text = overview
                    binding.tvReleasedetail.text = release
                    Glide.with(requireContext())
                        .load("https://image.tmdb.org/t/p/w500/$poster")
                        .into(binding.ivPosterdetail)
                }
                Status.ERROR -> {
                    // Handle error
                    Log.e("DetailMovieFragment", resources.message ?: "Unknown error")
                }
                Status.LOADING -> {
                    // Show loading indicator if necessary
                }
            }
        })
    }

}