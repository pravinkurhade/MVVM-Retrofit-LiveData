package com.example.codingassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.codingassignment.model.Continents
import com.example.codingassignment.repository.Repository

class CountriesViewModel : ViewModel() {

    private val continent: MutableLiveData<String> = MutableLiveData()

    val continents: LiveData<List<Continents>> = Transformations
        .switchMap(continent) {
            Repository.getContries(it)
        }

    fun setUserId(userId: String) {
        if (continent.value == userId) {
            return
        }
        continent.value = userId
    }

    fun cancelJobs() {
        Repository.cancelJobs()
    }
}















