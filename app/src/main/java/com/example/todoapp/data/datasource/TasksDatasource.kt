package com.example.todoapp.data.datasource

import com.example.todoapp.data.model.Tasks
import com.example.todoapp.roomdb.TasksDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksDatasource(var taskDao : TasksDao) {

    suspend fun getAllTasks() : List<Tasks> = withContext(Dispatchers.IO) {
        return@withContext taskDao.getAllTasks()
}

    suspend fun save(task_title:String,
                     task_contents : String,
                     task_date : String,
    ) {
        val newSave =  Tasks(0,task_title, task_contents, task_date)
        taskDao.save(newSave)
    }

    suspend fun update( task_id: Int,
                        task_title: String,
                        task_contents: String,
                        task_date: String
    ){
        val updatedTask = Tasks(task_id, task_title, task_contents, task_date)
        taskDao.update(updatedTask)

    }

    suspend fun delete(task_id : Int) {
        val deleted = Tasks(task_id, "", "", "", )
        taskDao.delete(deleted)
    }

    suspend fun search(searchKey : String) : List<Tasks> = withContext(Dispatchers.IO) {
        return@withContext taskDao.search(searchKey)
    }








}