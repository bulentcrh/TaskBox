package com.example.todoapp.uix.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.TextFieldColor
import com.example.todoapp.ui.theme.TextFieldTextColor

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    text : String
    ){
    TextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                Icons.Default.Create,
                contentDescription = "",
            )},
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)),
        placeholder = { Text(text, color = TextFieldTextColor) },
        maxLines = 4,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = TextFieldColor,
            focusedContainerColor = TextFieldColor,
            unfocusedTextColor = TextFieldTextColor,
            focusedTextColor = TextFieldTextColor,
        )
    )
}


