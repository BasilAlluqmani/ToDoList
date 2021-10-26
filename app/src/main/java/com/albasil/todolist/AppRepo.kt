package com.albasil.todolist

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepo(context: Context) {


    private val appDB = AppDataBase.getAppDataBase(context)!!


    suspend fun getAllTasks(): List<DataTask> = withContext(Dispatchers.IO) {

        appDB.taskDao.getAllTasks()
    }
    suspend fun fillDB() = withContext(Dispatchers.IO) {
        val dataDB = appDB.taskDao.getAllTasks()
        if (dataDB.isEmpty()) {
            for (i in 1..10) {
                val user = DataTask(
                    idTask =  i,
                    titleTask = "Tiltle $i",
                    descTask = "Description $i",
                    priority= "priority : mid"
                )
                appDB.taskDao.insert(user)
            }
        }
    }

}