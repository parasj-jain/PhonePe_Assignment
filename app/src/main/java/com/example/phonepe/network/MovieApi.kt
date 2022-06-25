package com.example.phonepe.network

import com.example.myrecipeapp.utils.APIConstants
import retrofit2.http.GET

interface MovieApi {

    @GET(APIConstants.MOVIE_NOW_PLAYING)
    suspend fun nowPlayingMovies(
//        @Query(APIConstants.PARAM_S) query: String
    ): String
}