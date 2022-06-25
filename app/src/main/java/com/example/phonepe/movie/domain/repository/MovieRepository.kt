package com.example.phonepe.movie.domain.repository

import com.example.phonepe.movie.data.model.MovieResult

interface MovieRepository {

    suspend fun fetchMovieList() : MovieResult

    suspend fun fetchMovieDetail() : MovieResult

}