package com.example.phonepe.movie.domain.model

import com.example.phonepe.common.APIConstants
import com.example.phonepe.movie.data.model.MovieDTO

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop_path: String
)

fun MovieDTO.toDomainMovie() : Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        backdrop_path = APIConstants.IMAGE_BASE_URL + this.poster_path
    )
}