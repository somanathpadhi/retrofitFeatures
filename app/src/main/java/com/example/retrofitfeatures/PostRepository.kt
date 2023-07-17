package com.example.retrofitfeatures

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(var apiService: APIService) {

    suspend fun getPost() = flow {
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)
}