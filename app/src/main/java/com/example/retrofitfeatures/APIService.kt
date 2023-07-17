package com.example.retrofitfeatures

import retrofit2.http.GET

interface APIService {

    @GET("/posts")
    suspend fun getPost(): List<Post>
}