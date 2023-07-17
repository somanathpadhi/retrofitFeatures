package com.example.retrofitfeatures

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.retrofitfeatures.ui.theme.RetrofitFeaturesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
     private val viewModel: PostViewModel by viewModels()


    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitFeaturesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    viewModel.getPost()
                    lifecycleScope.launchWhenCreated {
                        viewModel.posMutableStateFlow.collect{
                            when(it){
                                ResultState.Loading -> {
                                    Log.d("myLog","Now Loading...")
                                }
                                ResultState.empty -> {}
                                is ResultState.Failure -> {
                                    Log.d("myLog","Failed ${it.msg}")
                                }
                                is ResultState.Success -> {
                                    Log.d("myLog","Success ${it.data}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitFeaturesTheme {
        Greeting("Android")
    }
}