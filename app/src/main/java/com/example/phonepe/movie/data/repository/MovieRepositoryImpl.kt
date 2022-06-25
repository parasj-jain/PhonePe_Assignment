package com.example.phonepe.movie.data.repository

import com.example.phonepe.movie.data.model.MovieResult
import com.example.phonepe.movie.domain.repository.MovieRepository
import com.example.phonepe.network.MovieApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun fetchMovieList(): MovieResult {
        return movieApi.nowPlayingMovies()
    }

}