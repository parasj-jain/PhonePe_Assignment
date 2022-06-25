package com.example.phonepe.movie.domain.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonepe.common.Resource
import com.example.phonepe.movie.data.model.toDomainMovie
import com.example.phonepe.movie.domain.model.Movie
import com.example.phonepe.movie.domain.repository.MovieRepository
import com.example.phonepe.movie.domain.viewModel.MovieListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModelImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(), MovieListViewModel {

    private val _movieListFlow : MutableStateFlow<Resource<List<Movie>>> = MutableStateFlow(Resource.Loading())
    override val movieListFlow: StateFlow<Resource<List<Movie>>>
        get() = _movieListFlow

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _movieListFlow.value = Resource.Error(throwable.localizedMessage)
    }

    override fun fetchMovieList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val recipesResponse = movieRepository.fetchMovieList()
            val meal = recipesResponse.results.map { it.toDomainMovie() }
            _movieListFlow.value = Resource.Success(meal)
        }
    }


}