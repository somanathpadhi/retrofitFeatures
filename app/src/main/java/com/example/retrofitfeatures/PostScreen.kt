package com.example.retrofitfeatures

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PostScreen(viewModel: PostViewModel){
    val resultState by viewModel.posMutableStateFlow.collectAsState()
    when(val result = resultState){
        ResultState.Loading -> {
            Log.d("myLog","Now Loading...")
        }
        ResultState.empty -> {}
        is ResultState.Failure -> {
            Log.d("myLog","Failed ${result.msg}")
        }
        is ResultState.Success -> {
            Log.d("myLog","Success ${result.data}")
            LazyColumn {
                items(items = result.data){ item ->
                    Item(item)
                }
            }
        }
    }
}
@Composable
fun Item(post: Post) {
    Card(modifier = Modifier
        .padding(8.dp, 8.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = post.id.toString(), modifier = Modifier.padding(4.dp))
    }
}

@Preview
@Composable
fun ItemPreview(){
    val post = Post(id = 1, body = "hey", title = "Hello", userId = 2)
    Item(post = post)
}