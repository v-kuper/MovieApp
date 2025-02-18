package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Movies") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Magenta,
                        titleContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            MainContent(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun MainContent(modifier: Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "Hello!", modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp {}
}