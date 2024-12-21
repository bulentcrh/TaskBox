package com.example.todoapp.uix.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.ui.theme.TextFieldColor
import com.example.todoapp.uix.viewModel.HomePageViewModel

@Composable
fun SearchBar(
    homePageViewModel: HomePageViewModel,
    onValueChange: (String) -> Unit,
){
    val searchTf = remember { mutableStateOf( " ") }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .padding(start = 50.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape),
        value = searchTf.value,
        onValueChange = {
            searchTf.value = it
            homePageViewModel.search(it)
        },
        label = { Text(stringResource(id = R.string.search_label)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            ) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.White,
            unfocusedLabelColor = Color.Black,
            unfocusedIndicatorColor = Color.White,
            unfocusedContainerColor = TextFieldColor,
            focusedContainerColor = TextFieldColor
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )

    )
}