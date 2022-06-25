package com.example.phonepe.movie.persentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.phonepe.movie.domain.viewModel.MovieListViewModel
import com.example.phonepe.movie.domain.viewModel.impl.MovieListViewModelImpl

class MovieListActivity : AppCompatActivity() {

    private val viewModel : MovieListViewModel by viewModels<MovieListViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}