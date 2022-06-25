package com.example.phonepe.common.hilt

import com.example.phonepe.movie.data.repository.MovieRepositoryImpl
import com.example.phonepe.movie.domain.repository.MovieRepository
import com.example.phonepe.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApi: MovieApi
    ) : MovieRepository {
        return MovieRepositoryImpl(
            movieApi = movieApi
        )
    }

}