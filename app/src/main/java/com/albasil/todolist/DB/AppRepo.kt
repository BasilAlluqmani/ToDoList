package com.albasil.todolist.DB

import android.content.Context
import androidx.lifecycle.LiveData
import com.albasil.todolist.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepo(context: Context) {
   // class AppRepo (private val taskDao: TaskDao){

    private val appDB = AppDataBase.getAppDataBase(context)!!



    companion object{
        var taskList = mutableListOf<DataTask>()
        var nextIdList = 0
    }


    //from room database
        suspend fun getAllTasks(): List<DataTask> = withContext(Dispatchers.IO) {

        appDB.taskDao.getAllTasks()
    }



   /* fun getAllTaskFromList(): List<DataTask> {

        /*if (taskList.isEmpty()) {
            taskList += DataTask(0, "HJ", "SSS","2030/05/05","2030/08/02",false)
            taskList += DataTask(1, "HJ", "SSS","2030/05/05","2030/08/02",false)
            taskList += DataTask(2, "HJ", "SSS","2030/05/05","2030/08/02",false)
            nextIdList= 3
        }*/
        return taskList
    }
*/

    fun insertTask(itemTask : DataTask){
        taskList += itemTask
        nextIdList++

    }

    suspend fun insertTaskToDB(addTask: DataTask){
        appDB.taskDao.insert(addTask)

    }

    suspend fun deleteTask(task: DataTask){
        appDB.taskDao.delete(task)

    }

    suspend fun deleteAllTasks(){

        appDB.taskDao.deleteAllTasks()
    }


    suspend fun editTask(index: Int, taskTitle:String, taskDec:String, isCeck:Boolean,taskDueDate:String){
        taskList[index].titleTask=taskTitle
        taskList[index].descTask=taskDec
        taskList[index].ifCheck=isCeck
        taskList[index].due_date=taskDueDate


    }

    suspend fun updateTask(task:DataTask){
        appDB.taskDao.update(task)
    }


/*
   suspend fun fillDB() = withContext(Dispatchers.IO) {
        val dataDB = appDB.taskDao.getAllTasks()
        if (dataDB.isEmpty()) {
            for (i in 1..10) {
                val user = DataTask(
                    idTask =  i,
                    titleTask = "Tiltle $i",
                    descTask = "Description $i",
                    due_date = "55555555",
                    ifCheck = false,
                    creation_date = "2020/08/23"
                )
                appDB.taskDao.insert(user)
            }
        }
    }
*/
}