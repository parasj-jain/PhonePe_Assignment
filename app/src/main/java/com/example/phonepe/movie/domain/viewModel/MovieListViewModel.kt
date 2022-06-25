package com.example.phonepe.movie.domain.viewModel

import com.example.phonepe.common.Resource
import com.example.phonepe.movie.domain.model.Movie
import kotlinx.coroutines.flow.StateFlow

interface MovieListViewModel {

    val movieListFlow : StateFlow<Resource<List<Movie>>>

    fun fetchMovieList()

}