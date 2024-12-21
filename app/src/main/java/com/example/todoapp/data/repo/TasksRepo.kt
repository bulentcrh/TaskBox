package com.example.todoapp.data.repo

import android.icu.text.StringSearch
import com.example.todoapp.data.datasource.TasksDatasource
import com.example.todoapp.data.model.Tasks

class TasksRepo(var tds : TasksDatasource) {

    suspend fun getAllTasks() : List<Tasks> = tds.getAllTasks()

    suspend fun save(task_title:String,
                     task_contents : String,
                     task_date : String,

    ) = tds.save(task_title, task_contents, task_date)

    suspend fun update(task_id: Int,
                       task_title: String,
                       task_contents: String,
                       task_date: String

    ) = tds.update(task_id, task_title, task_contents, task_date)

    suspend fun delete(task_id : Int) = tds.delete(task_id)

    suspend fun search(searchKey : String) : List<Tasks> = tds.search(searchKey)




}