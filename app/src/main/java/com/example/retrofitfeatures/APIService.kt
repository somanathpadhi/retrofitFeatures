package com.example.retrofitfeatures

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface APIService {

    @GET("/posts")
    suspend fun getPost(): List<Post>
}