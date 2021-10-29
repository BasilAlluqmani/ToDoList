package com.albasil.todolist.DB

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tasks_table")
data class DataTask(
    @PrimaryKey(autoGenerate = true) val idTask: Int=0,
//   @ColumnInfo(name = "id")
    var titleTask: String,
    var descTask: String,
    val creation_date: String,
    var due_date: String,

    var ifCheck:Boolean
) : Parcelable {}
