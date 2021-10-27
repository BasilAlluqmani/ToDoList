package com.albasil.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.albasil.todolist.DB.AppRepo
import com.albasil.todolist.DB.DataTask

class TaskVM(context: Application) : AndroidViewModel(context){

    val repo : AppRepo = AppRepo(context)

    fun getAllTaskFromList(): List<DataTask>{

        return repo.getAllTaskFromList()

    }
    fun insertTask(insertTask: DataTask){
        repo.insertTask(insertTask)
    }





    /*private val repo = AppRepo(context)
    fun getAllTasks():MutableLiveData<List<DataTask>>{
        val tasks = MutableLiveData<List<DataTask>>()
        viewModelScope.launch {

            //note 1--------------------***********---------------
           // tasks.postValue(repo.appDB)

        }
        return tasks
    }
    fun fillDB()= viewModelScope.launch {
        repo.fillDB()

    }
*/
}