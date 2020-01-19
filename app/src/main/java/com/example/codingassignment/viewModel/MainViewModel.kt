package com.example.codingassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.codingassignment.model.Continents
import com.example.codingassignment.repository.Repository

class MainViewModel: ViewModel() {

    var continents: LiveData<List<Continents>> = Repository.getContinents()

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}















