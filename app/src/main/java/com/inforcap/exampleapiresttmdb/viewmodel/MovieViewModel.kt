package com.inforcap.exampleapiresttmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inforcap.exampleapiresttmdb.core.Constants
import com.inforcap.exampleapiresttmdb.model.MovieEntity
import com.inforcap.exampleapiresttmdb.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {

    private var _movieList = MutableLiveData<List<MovieEntity>>()
    val movieList: LiveData<List<MovieEntity>> = _movieList

    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.getAllMovies(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                _movieList.value = response.body()!!.results.sortedBy { it.title }
            }
        }
    }

    fun getPopular() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.getPopular(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                _movieList.value = response.body()!!.results.sortedByDescending { it.popularity.toFloat() }
            }
        }
    }

    fun getTopRated() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.getTopRated(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                _movieList.value = response.body()!!.results.sortedByDescending { it.rating.toFloat() }
            }
        }
    }

    fun getUpcoming() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.getUpcoming(Constants.API_KEY)
            withContext(Dispatchers.Main) {
                _movieList.value = response.body()!!.results.sortedBy { it.title }
            }
        }
    }




}