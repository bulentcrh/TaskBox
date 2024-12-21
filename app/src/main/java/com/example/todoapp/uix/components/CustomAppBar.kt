package com.example.todoapp.uix.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.ui.theme.permanetmarker
import com.example.todoapp.uix.viewModel.ThemeViewModel

@Composable
    fun CustomAppBar(
        themeViewModel: ThemeViewModel,
        isDarkTheme: Boolean
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.pp),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                stringResource(R.string.app_name),
                fontFamily = permanetmarker,
                fontSize = 30.sp,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.width(170.dp))
            IconButton(
                onClick = {themeViewModel.toggleTheme()}
            ) {
                Icon(
                     painter = painterResource(
                        id = if (isDarkTheme) R.drawable.baseline_light_mode_24 else R.drawable.dark_mode_icon
                     ),
                    contentDescription = "null"
                )
            }
        }
    }