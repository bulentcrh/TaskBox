package com.example.todoapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.model.Tasks

@Dao
interface TasksDao {

    @Query("SELECT * FROM Tasks")
    suspend fun getAllTasks() : List<Tasks>

    @Insert
    suspend fun save(tasks: Tasks)

    @Delete
    suspend fun delete(tasks: Tasks)

    @Update
    suspend fun update(tasks: Tasks)

    @Query("SELECT * FROM Tasks WHERE task_title like '%' || :searchKey || '%' ")
    suspend fun search(searchKey : String) : List<Tasks>
}