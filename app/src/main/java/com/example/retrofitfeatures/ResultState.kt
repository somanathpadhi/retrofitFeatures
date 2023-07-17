package com.example.retrofitfeatures

sealed class ResultState{
    object Loading: ResultState()
    class Failure(val msg: String): ResultState()
    class Success(val data: List<Post>): ResultState()
    object empty: ResultState()
}

