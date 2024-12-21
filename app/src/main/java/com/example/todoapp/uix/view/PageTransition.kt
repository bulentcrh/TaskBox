package com.example.todoapp.uix.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.data.model.Tasks
import com.example.todoapp.uix.viewModel.DetailPageViewModel
import com.example.todoapp.uix.viewModel.HomePageViewModel
import com.example.todoapp.uix.viewModel.RegisterPageViewModel
import com.example.todoapp.uix.viewModel.ThemeViewModel
import com.google.gson.Gson

@ExperimentalMaterial3Api
@Composable
fun PageTransition(homePageViewModel: HomePageViewModel,
                   detailPageViewModel: DetailPageViewModel,
                   registerPageViewModel: RegisterPageViewModel,
                   navController: NavHostController,
                   themeViewModel: ThemeViewModel,
) {

    NavHost(navController = navController, startDestination = "homePage"){

        composable("homePage") {
            HomePage(navController,homePageViewModel,themeViewModel)
        }

        composable("registerPage"){
            RegisterPage(registerPageViewModel,navController)
        }

        composable("detailPage/{task}",
            arguments = listOf(
                navArgument("task") {type = NavType.StringType}
            )
        ){
            val json = it.arguments?.getString("task")
            val article = Gson().fromJson(json, Tasks::class.java)
            DetailPage(article, detailPageViewModel,navController)
        }
    }

}