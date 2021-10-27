package com.albasil.todolist.DB

import android.content.Context

class AppRepo(context: Context) {
    companion object{
        var taskList = mutableListOf<DataTask>()
        var nextIdList = 0
    }


    fun getAllTaskFromList(): List<DataTask> {

        if (taskList.isEmpty()) {
            taskList += DataTask(0, "HJ", "SSS","2030/05/05","2030/08/02","jjj",false)
            taskList += DataTask(1, "HJ", "SSS","2030/05/05","2030/08/02","jjj",false)
            taskList += DataTask(2, "HJ", "SSS","2030/05/05","2030/08/02","jjj",false)
            nextIdList= 3
        }
        return taskList
    }


    fun insertTask(itemTask : DataTask){
        taskList += itemTask
        nextIdList++

    }

    fun deleteTask(index : Int){
        taskList.removeAt(index)

    }

    fun editTask(index : Int,taskTitle:String, taskDec:String,isCeck:Boolean){
        taskList[index].titleTask=taskTitle
        taskList[index].descTask=taskDec
        taskList[index].ifCheck=isCeck


    }


/*
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
    }*/

}