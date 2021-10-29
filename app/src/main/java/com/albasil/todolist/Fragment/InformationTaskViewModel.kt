package com.albasil.todolist.Fragment

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albasil.todolist.DB.AppRepo
import com.albasil.todolist.DB.DataTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InformationTaskViewModel(context: Application) : AndroidViewModel(context) {

    val repo: AppRepo = AppRepo(context)

    suspend fun editTask(
        index: Int,
        taskTitle: String,
        taskDec: String,
        isCheck: Boolean,
        taskDueDate: String
    ) {

        repo.editTask(index, taskTitle, taskDec, isCheck, taskDueDate)
    }

     fun updateTask(task: DataTask) {
        viewModelScope.launch {
            repo.updateTask(task)
        }


    }


     fun deleteTask(task: DataTask) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTask(task)
        }

    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllTasks()
        }

    }


}