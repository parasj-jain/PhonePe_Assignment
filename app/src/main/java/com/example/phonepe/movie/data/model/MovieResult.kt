package com.example.phonepe.movie.data.model

data class MovieResult(
    val dates: Dates,
    val page: Int,
    val results: List<MovieDTO>,
    val total_pages: Int,
    val total_results: Int
)