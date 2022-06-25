package com.example.phonepe.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.phonepe.common.Resource
import com.example.phonepe.databinding.ActivityMovieDetailBinding
import com.example.phonepe.movie.domain.model.MovieDetail
import com.example.phonepe.movie.domain.viewModel.MovieDetailViewModel
import com.example.phonepe.movie.domain.viewModel.impl.MovieDetailViewModelImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel : MovieDetailViewModel by viewModels<MovieDetailViewModelImpl>()
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        registerObservers()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        viewModel.fetchMovieDetail()
    }

    private fun registerObservers() {
        lifecycleScope.launch {
            viewModel.movieDetailFlow.collect {
                handleResource(it)
            }
        }
    }

    private fun handleResource(resource: Resource<MovieDetail>) {
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
        binding.content.visibility = View.GONE
        binding.loading.root.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.root.visibility = View.VISIBLE
        binding.content.visibility = View.GONE
        binding.error.root.visibility = View.GONE
    }

    private fun showSuccess(movie: MovieDetail) {
        binding.content.visibility = View.VISIBLE
        binding.loading.root.visibility = View.GONE
        binding.error.root.visibility = View.GONE

        binding.releaseDateValue.text = movie.release_date
        binding.popularityValue.text = movie.popularity.toString()
        binding.ratingValue.text = movie.average.toString()
        binding.subtitle.text = movie.overview

        binding.toolbar.title = movie.title

    }

}