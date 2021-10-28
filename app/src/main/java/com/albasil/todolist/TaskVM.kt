package com.albasil.todolist

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import androidx.room.Update
import com.albasil.todolist.DB.AppRepo
import com.albasil.todolist.DB.DataTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskVM(context: Application) : AndroidViewModel(context){

    val repo : AppRepo = AppRepo(context)

    fun getAllTasks(): MutableLiveData<List<DataTask>> {
        val taskDao:RoomDatabase

        val tasks = MutableLiveData<List<DataTask>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return tasks
    }

    //Done
    // add to room database
    fun addTask(_insertTask: DataTask){
        viewModelScope.launch (Dispatchers.IO){
            repo.insertTaskToDB(_insertTask)

        }

    }


 /*   fun getAllTaskFromList(): List<DataTask>{

        return repo.getAllTaskFromList()

    }*/
    fun insertTask(insertTask: DataTask){
        repo.insertTask(insertTask)
    }


    fun fillDB()= viewModelScope.launch {
        //repo.fillDB()
        repo.getAllTasks()

    }


    fun update(update: DataTask ){
      //repo.editTask()
}



   /* fun getAllTasks(): MutableLiveData<List<DataTask>> {
        val tasks = MutableLiveData<List<DataTask>>()
        viewModelScope.launch {

            //note 1--------------------***********---------------
           // tasks.postValue(repo.appDB)

        }
        return tasks
    }*/





}