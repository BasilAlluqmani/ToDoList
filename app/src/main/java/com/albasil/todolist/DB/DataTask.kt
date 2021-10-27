package com.albasil.todolist.DB

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tasks_table")
data class DataTask(
    @PrimaryKey
    val idTask: Int,
    var titleTask: String,
    var descTask: String,
    val creation_date: String,
    val due_date: String,
    var priority: String,
    var ifCheck:Boolean
) : Parcelable {}
