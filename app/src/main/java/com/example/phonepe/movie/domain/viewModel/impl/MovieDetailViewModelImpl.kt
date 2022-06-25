package com.example.phonepe.movie.domain.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonepe.common.Resource
import com.example.phonepe.movie.domain.model.MovieDetail
import com.example.phonepe.movie.domain.model.toDomainMovie
import com.example.phonepe.movie.domain.model.toDomainMovieDetail
import com.example.phonepe.movie.domain.repository.MovieRepository
import com.example.phonepe.movie.domain.viewModel.MovieDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModelImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(), MovieDetailViewModel {

    private val _movieDetailFlow : MutableStateFlow<Resource<MovieDetail>> = MutableStateFlow(Resource.Loading())
    override val movieDetailFlow: StateFlow<Resource<MovieDetail>>
        get() = _movieDetailFlow

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _movieDetailFlow.value = Resource.Error(throwable.localizedMessage)
    }

    override fun fetchMovieDetail() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val recipesResponse = movieRepository.fetchMovieDetail()
            val movie = recipesResponse.results.map { it.toDomainMovieDetail() }.first()
            _movieDetailFlow.value = Resource.Success(movie)
        }
    }
}