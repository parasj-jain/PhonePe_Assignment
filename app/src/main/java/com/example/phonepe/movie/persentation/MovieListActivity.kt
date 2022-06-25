package com.example.phonepe.movie.persentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.phonepe.common.Resource
import com.example.phonepe.databinding.ActivityMovieListBinding
import com.example.phonepe.movie.domain.model.Movie
import com.example.phonepe.movie.domain.viewModel.MovieListViewModel
import com.example.phonepe.movie.domain.viewModel.impl.MovieListViewModelImpl
import com.example.phonepe.movie.persentation.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private val viewModel : MovieListViewModel by viewModels<MovieListViewModelImpl>()
    private lateinit var binding: ActivityMovieListBinding

    // region override methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    // endregion

    // region private methods

    private fun init() {
        binding.recyclerView.adapter = MovieAdapter(mutableListOf(), ::movieClicked)
        registerObservers()
        viewModel.fetchMovieList()
    }

    private fun registerObservers() {
        lifecycleScope.launch {
            viewModel.movieListFlow.collect {
                handleResource(it)
            }
        }
    }

    private fun movieClicked(movie: Movie) {
        Log.e(MovieListActivity::class.java.simpleName, "Movie ${movie.title} Clicked")
    }

    private fun handleResource(resource: Resource<List<Movie>>) {
        when (resource) {
            is Resource.Success -> {
                showSuccess(resource.data)
            }
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Error -> {
                showError()
            }
        }
    }

    private fun showError() {
        binding.error.root.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.loading.root.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.root.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.error.root.visibility = View.GONE
    }

    private fun showSuccess(movies: List<Movie>) {
        binding.recyclerView.visibility = View.VISIBLE
        binding.loading.root.visibility = View.GONE
        binding.error.root.visibility = View.GONE

        (binding.recyclerView.adapter as? MovieAdapter)?.updateData(movies)
    }

    // endregion
}