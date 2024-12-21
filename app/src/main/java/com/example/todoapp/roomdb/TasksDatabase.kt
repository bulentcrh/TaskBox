package com.example.todoapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.model.Tasks

@Database(entities = [Tasks::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun getTasksDao() : TasksDao
}