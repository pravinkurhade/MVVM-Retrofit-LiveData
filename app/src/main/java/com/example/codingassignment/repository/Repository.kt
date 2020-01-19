package com.example.codingassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.codingassignment.api.MyRetrofitBuilder
import com.example.codingassignment.model.Continents
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getContinents(): LiveData<List<Continents>> {
        Log.d("Repository","called")
        job = Job()
        return object: LiveData<List<Continents>>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getContinents()
                        withContext(Main){
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun getContries(continent: String): LiveData<List<Continents>>{
        job = Job()
        return object: LiveData<List<Continents>>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getContries(continent)
                        withContext(Main){
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }


    fun cancelJobs(){
        job?.cancel()
    }

}
















