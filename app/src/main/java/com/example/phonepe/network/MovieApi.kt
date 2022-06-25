package com.example.phonepe.network

import com.example.phonepe.common.APIConstants
import com.example.phonepe.movie.data.model.MovieResult
import retrofit2.http.GET

interface MovieApi {

    @GET(APIConstants.MOVIE_NOW_PLAYING)
    suspend fun nowPlayingMovies(): MovieResult
}