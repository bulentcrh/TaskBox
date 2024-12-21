package com.example.todoapp.uix.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class ThemeViewModel : ViewModel() {
    val isDarkTheme = MutableLiveData(false)

    fun toggleTheme() {
        isDarkTheme.value = !(isDarkTheme.value ?: false)
    }
}