package com.example.phonepe.movie.data.model

import com.example.phonepe.common.APIConstants
import com.example.phonepe.movie.domain.model.Movie

data class MovieDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDTO.toDomainMovie() : Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        backdrop_path = APIConstants.IMAGE_BASE_URL + this.backdrop_path
    )
}