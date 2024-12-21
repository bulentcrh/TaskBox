package com.example.todoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.todoapp.data.datasource.TasksDatasource
import com.example.todoapp.data.repo.TasksRepo
import com.example.todoapp.roomdb.TasksDao
import com.example.todoapp.roomdb.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule() {

    @Provides
    @Singleton
    fun provideTasksRepo (tds : TasksDatasource) : TasksRepo{
        return TasksRepo(tds)
    }

    @Provides
    @Singleton
    fun provideTaskDatasource(tasksDao: TasksDao) : TasksDatasource{
        return  TasksDatasource(tasksDao)
    }

    @Provides
    @Singleton
    fun provideTasksDao(@ApplicationContext context: Context) : TasksDao{
        val db = Room.databaseBuilder(context, TasksDatabase::class.java, "Tasks").build()
        return db.getTasksDao()
    }


}