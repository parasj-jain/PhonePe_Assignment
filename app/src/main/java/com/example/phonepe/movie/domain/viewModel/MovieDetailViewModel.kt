package com.example.phonepe.movie.domain.viewModel

import com.example.phonepe.common.Resource
import com.example.phonepe.movie.domain.model.MovieDetail
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailViewModel {

    val movieDetailFlow : StateFlow<Resource<MovieDetail>>

    fun fetchMovieDetail()

}