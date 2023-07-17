package com.example.retrofitfeatures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(var repository: PostRepository): ViewModel() {
    private var _postMutableStateFlow: MutableStateFlow<ResultState> = MutableStateFlow(ResultState.empty)
    var posMutableStateFlow: StateFlow<ResultState>  = _postMutableStateFlow


     fun getPost() = viewModelScope.launch {
        _postMutableStateFlow.value = ResultState.Loading

        repository.getPost().
            catch { e->
                _postMutableStateFlow.value = ResultState.Failure(e.message.toString())
            }
            .collect{
            _postMutableStateFlow.value = ResultState.Success(it)
        }
    }
}