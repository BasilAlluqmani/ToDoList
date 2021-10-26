package com.albasil.todolist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "tasks_table")
data class DataTask(
    @PrimaryKey
    val idTask: Int,
    val titleTask: String,
    val descTask:String,
    //val creation_date: String,
   // val due_date: String,
    val priority: String


) {}
