package com.example.codingassignment.api

import com.example.codingassignment.model.Continents
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all")
    suspend fun getContinents(): List<Continents>

    @GET("region/{continent}")
    suspend fun getContries(@Path("continent") userId: String): List<Continents>

}