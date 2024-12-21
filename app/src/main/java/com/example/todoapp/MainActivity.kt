package com.example.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.uix.components.CustomAppBar
import com.example.todoapp.uix.components.MyBottomBar
import com.example.todoapp.uix.view.PageTransition
import com.example.todoapp.uix.viewModel.DetailPageViewModel
import com.example.todoapp.uix.viewModel.HomePageViewModel
import com.example.todoapp.uix.viewModel.RegisterPageViewModel
import com.example.todoapp.uix.viewModel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme = themeViewModel.isDarkTheme.observeAsState(false).value
            ToDoAppTheme(darkTheme = isDarkTheme) {
                MyBottomBar(themeViewModel)
            }
        }
    }
}
