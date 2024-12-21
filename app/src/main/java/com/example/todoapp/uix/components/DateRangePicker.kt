package com.example.todoapp.uix.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.ui.theme.poppins
import java.text.DateFormat
import java.util.Date

@ExperimentalMaterial3Api
@Composable
fun CustomDateRangePicker(
    onDateSelected: (String) -> Unit,
    showDatePicker: MutableState<Boolean>,
    ) {
    val dateRangePickerState = rememberDateRangePickerState()

    if (showDatePicker.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val startDate = dateRangePickerState.selectedStartDateMillis
                        val endDate = dateRangePickerState.selectedEndDateMillis
                        if (startDate != null && endDate != null) {
                            val formattedDate =
                                " ${DateFormat.getDateInstance().format(Date(startDate))} " +
                                        "|| ${DateFormat.getDateInstance().format(Date(endDate))}"
                            onDateSelected(formattedDate)
                        }
                        showDatePicker.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.ok), fontFamily = poppins, color = MaterialTheme.colorScheme.onBackground)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker.value = false }) {
                    Text(stringResource(id = R.string.cancel), fontFamily = poppins, color = MaterialTheme.colorScheme.onBackground)
                }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                title = { Text(stringResource(id = R.string.select_date_range),  fontFamily = poppins) },
                showModeToggle = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(16.dp)
            )
        }
    }



}