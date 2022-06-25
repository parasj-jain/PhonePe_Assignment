package com.example.phonepe.movie.domain.model

import com.example.phonepe.common.APIConstants
import com.example.phonepe.movie.data.model.MovieDTO

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop_path: String,
    val average: Double,
    val popularity: Double,
    val release_date: String
)

fun MovieDTO.toDomainMovieDetail() : MovieDetail {
    return MovieDetail(
        id = this.id,
        title = this.title,
        overview = this.overview,
        backdrop_path = APIConstants.IMAGE_BASE_URL + this.poster_path,
        average = this.vote_average,
        popularity = this.popularity,
        release_date = this.release_date
    )
}
