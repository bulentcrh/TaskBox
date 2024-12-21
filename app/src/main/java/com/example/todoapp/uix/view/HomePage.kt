package com.example.todoapp.uix.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.ui.theme.permanetmarker
import com.example.todoapp.ui.theme.poppins
import com.example.todoapp.uix.components.CustomAppBar
import com.example.todoapp.uix.components.SearchBar
import com.example.todoapp.uix.viewModel.HomePageViewModel
import com.example.todoapp.uix.viewModel.ThemeViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavController,
    homePageViewModel: HomePageViewModel,
    themeViewModel: ThemeViewModel
){
    val tasksList = homePageViewModel.tasksList.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val isDarkTheme = themeViewModel.isDarkTheme.observeAsState(false).value


    LaunchedEffect(Unit) {
        homePageViewModel.getAllTasks()
    }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        CustomAppBar(themeViewModel, isDarkTheme)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            snackbarHost = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        ){ paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ){
                item {
                    Spacer(Modifier.height(10.dp))

                    SearchBar(
                        homePageViewModel,
                        onValueChange = { homePageViewModel.search(it) }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            stringResource(id = R.string.my_task),
                            fontFamily = poppins,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        TextButton(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.primary,
                                containerColor = Color.Transparent
                            )
                        ){
                            Text(
                                stringResource(id = R.string.see_all),
                                fontFamily = poppins,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                if (tasksList.value.isNotEmpty()) {
                    items(count = tasksList.value.count()) { index ->
                        val task = tasksList.value[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(horizontal = 20.dp, vertical = 5.dp)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable {
                                    val taskJson = Gson().toJson(task)
                                    navController.navigate("detailPage/$taskJson")
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    Modifier.padding(10.dp),
                                ) {
                                    Text(
                                        task.task_title,
                                        fontFamily = poppins,
                                        maxLines = 1,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = task.task_date,
                                        fontFamily = poppins,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                        ),
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            val sb = snackbarHostState.showSnackbar(
                                                message = "${task.task_title} do you want to delete?",
                                                actionLabel = "YES",
                                                withDismissAction = true
                                            )
                                            if (sb == SnackbarResult.ActionPerformed) {
                                                homePageViewModel.delete(task.task_id)
                                            }
                                        }
                                    }) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "",
                                        modifier = Modifier.size(15.dp),
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                } else {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.task_add_image),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(350.dp)
                                    .padding(bottom = 16.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.no_tasks),
                                fontFamily = permanetmarker,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(0.8f)
                            )
                        }
                    }
                }
            }
        }



}
