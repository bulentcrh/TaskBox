package com.example.todoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tasks (

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "task_id")
    var task_id : Int,
    @ColumnInfo(name = "task_title")
    var task_title : String,
    @ColumnInfo(name = "task_contents")
    var task_contents : String,
    @ColumnInfo(name = "task_date")
    var task_date : String,
)