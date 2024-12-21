package com.example.todoapp.uix.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.model.Tasks
import com.example.todoapp.data.repo.TasksRepo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(var tasksRepo: TasksRepo) :ViewModel() {

    val tasksList = MutableLiveData<List<Tasks>>()

    init {
        getAllTasks()
    }


    fun getAllTasks() {
        CoroutineScope(Dispatchers.Main).launch {
            tasksList.value = tasksRepo.getAllTasks()
        }
    }

    fun delete(task_id : Int) {
        CoroutineScope(Dispatchers.Main).launch {
            tasksRepo.delete(task_id)
            getAllTasks()
        }
    }

    fun search(searchKey: String) {
        CoroutineScope(Dispatchers.Main).launch {
            tasksList.value =  tasksRepo.search(searchKey)
        }
    }
}