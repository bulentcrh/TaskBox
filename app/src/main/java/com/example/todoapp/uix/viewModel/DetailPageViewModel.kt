package com.example.todoapp.uix.viewModel

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repo.TasksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor( val tRepo : TasksRepo) :ViewModel() {

    fun update(
        task_id: Int,
        task_title: String,
        task_contents: String,
        task_date: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            tRepo.update(task_id, task_title, task_contents, task_date)
        }

    }
}