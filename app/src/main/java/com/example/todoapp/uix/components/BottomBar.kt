package com.example.todoapp.uix.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.todoapp.R
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.poppins
import com.example.todoapp.uix.view.PageTransition
import com.example.todoapp.uix.viewModel.ThemeViewModel

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyBottomBar(
    themeViewModel: ThemeViewModel,
) {
    val navigationController = rememberNavController()
    val selected = remember { mutableStateOf(0) }


    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                ) {
                NavigationBarItem(
                    selected = selected.value == 0,
                    onClick = {
                        selected.value = 0
                        navigationController.navigate("homePage")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            tint = if (selected.value == 0) MaterialTheme.colorScheme.primary else Color.Gray,
                        )
                    },
                    label = { Text(stringResource(id = R.string.home) , fontFamily = poppins) }
                )
                NavigationBarItem(
                    selected = selected.value == 1,
                    onClick = {
                        selected.value = 1
                        navigationController.navigate("homePage")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            tint = if (selected.value == 1) MaterialTheme.colorScheme.primary  else Color.Gray,
                        )
                    },
                    label = {Text(stringResource(id = R.string.my_tasks),fontFamily = poppins) }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(
                        onClick = { navigationController.navigate("registerPage") },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground,)
                    }
                }
                NavigationBarItem(
                    selected = selected.value == 2,
                    onClick = {
                        selected.value = 2
                        navigationController.navigate("homePage")
                    },
                    icon = {
                        Icon(
                            Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = if (selected.value == 2) MaterialTheme.colorScheme.primary else Color.Gray,
                        )
                    },
                    label = { Text(stringResource(id = R.string.favorite),fontFamily = poppins) }
                )
                NavigationBarItem(
                    selected = selected.value == 3,
                    onClick = {
                        selected.value = 3
                        navigationController.navigate("homePage")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = if (selected.value == 3) MaterialTheme.colorScheme.primary  else Color.Gray,
                        )
                    },
                    label = { Text(stringResource(id = R.string.account), fontFamily = poppins) }
                )
            }
        }
    ) {
        PageTransition(
            homePageViewModel = viewModel(),
            detailPageViewModel = viewModel(),
            registerPageViewModel = viewModel(),
            navController = navigationController,
            themeViewModel = themeViewModel,
        )
    }
}
