package com.yudhi.moviedatabase.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yudhi.moviedatabase.R
import com.yudhi.moviedatabase.databinding.FragmentMovieBinding
import com.yudhi.domain.helper.MyDataStore
import com.yudhi.moviedatabase.presentation.viewmodel.MovieViewModel
import com.yudhi.domain.common.Status
import com.yudhi.moviedatabase.presentation.adapter.MovieAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModel()
    private val MyDataStore: MyDataStore by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMovieBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            val (username, password, email) = MyDataStore.getSavedAccount().first()
            binding.tvWelcome.text = "Welcome, $username"
        }


        viewModel.getMoviePopular().observe(viewLifecycleOwner) { resources ->
            when (resources.status) {
                Status.SUCCESS -> {
                    val movies = resources.data
                    val resultMovie = movies
                    movieAdapter = MovieAdapter(resultMovie)
                    recyclerView.adapter = movieAdapter
                }
                Status.ERROR -> {
                    // Handle error
                    Log.e("PopularMoviesFragment", resources.message ?: "Unknown error")
                }
                Status.LOADING -> {
                    // Show loading indicator if necessary
                }
            }
        }
        binding.btnProfile.setOnClickListener {
            view.findNavController().navigate(R.id.action_movieFragment_to_profileFragment)
        }
    }

}