package com.albasil.todolist

import androidx.room.*
import com.albasil.todolist.DB.DataTask


@Dao
interface TaskDao {


    @Insert
    suspend fun insert(data_task: DataTask)

   @Query("select * From tasks_table ")
    suspend fun getAllTasks() : List<DataTask>


    @Update()
    suspend fun update(task: DataTask) {
    }

    @Delete
    suspend fun delete(task: DataTask) {
    }

    @Query("select * from tasks_table where idTask== :tId")
    suspend fun selectUserById(tId: Int): DataTask



}