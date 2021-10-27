package com.albasil.todolist

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.room.RoomDatabase
import androidx.room.Update
import com.albasil.todolist.DB.AppRepo
import com.albasil.todolist.DB.DataTask

class TaskVM(context: Application) : AndroidViewModel(context){


    val repo : AppRepo = AppRepo(context)



    init {

    }
    fun getAllTasks(){
        val taskDao:RoomDatabase
    }
    fun getAllTaskFromList(): List<DataTask>{

        return repo.getAllTaskFromList()

    }
    fun insertTask(insertTask: DataTask){
        repo.insertTask(insertTask)
    }

  fun update(update: DataTask ){
      //repo.editTask()
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