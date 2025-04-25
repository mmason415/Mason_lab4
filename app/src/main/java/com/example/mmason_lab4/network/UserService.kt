package com.example.mmason_lab4.network

import com.example.mmason_lab4.model.RandomUserResponse
import retrofit2.http.GET

interface UserService {
    @GET("api/?results=20")
    suspend fun getUsers(): RandomUserResponse
}
