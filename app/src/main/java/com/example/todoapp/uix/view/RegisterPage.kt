package com.example.todoapp.uix.view

import android.annotation.SuppressLint
import android.widget.Toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.ui.theme.permanetmarker
import com.example.todoapp.ui.theme.poppins
import com.example.todoapp.uix.components.CustomDateRangePicker
import com.example.todoapp.uix.components.CustomTextField
import com.example.todoapp.uix.components.DatePickerBox
import com.example.todoapp.uix.viewModel.RegisterPageViewModel

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterPage(
    registerPageViewModel: RegisterPageViewModel,
    navController: NavController,
) {
    val taskTitle = remember { mutableStateOf("") }
    val taskContents = remember { mutableStateOf("") }
    val taskDate = remember { mutableStateOf(" ") }
    val showDatePicker = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    stringResource(R.string.task_register_title),
                    textAlign = TextAlign.Center,
                    fontFamily = permanetmarker
                ) },
            navigationIcon = {
                IconButton(onClick = {navController.navigate("homePage")}) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "",
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(stringResource(R.string.task_title_label), textAlign = TextAlign.Start, fontFamily = poppins)
            CustomTextField(
                onValueChange = {taskTitle.value = it},
                value = taskTitle.value,
                text = stringResource(R.string.task_title_ph)
            )
            Spacer(modifier = Modifier.size(15.dp))

            Text(stringResource(R.string.task_description_label), fontFamily = poppins)
            CustomTextField(
                onValueChange = {taskContents.value = it},
                value = taskContents.value,
                text = stringResource(R.string.task_description_ph)
            )
            Spacer(modifier = Modifier.size(15.dp))

            Text(stringResource(R.string.select_date_label), fontFamily = poppins)
            CustomDateRangePicker(
                onDateSelected = { selectedDate ->
                    taskDate.value = selectedDate
                },
                showDatePicker = showDatePicker
            )

            DatePickerBox(
                onClick = {showDatePicker.value = true},
                text =  if (taskDate.value.isEmpty()) stringResource(R.string.select_date_label) else taskDate.value,
                color = if (taskDate.value.isEmpty()) Color.Gray else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                onClick = {
                    if (taskTitle.value.isBlank() || taskContents.value.isBlank() || taskDate.value.isBlank()) {
                        Toast.makeText(context,"Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }else {
                        registerPageViewModel.save(
                        taskTitle.value,
                        taskContents.value,
                        taskDate.value,
                    )
                    navController.navigate("homePage")
                   }
                }
            ) {
                Text(
                    stringResource(R.string.register_task_button),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontFamily = poppins
                )
            }
        }
    }
}


