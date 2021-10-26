package com.albasil.todolist

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskVM(context: Application) : AndroidViewModel(context){

    private val repo = AppRepo(context)

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

}