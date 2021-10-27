package com.albasil.todolist

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.albasil.todolist.DB.AppRepo

class InformationTaskViewModel(context: Application) : AndroidViewModel(context) {

    val repo : AppRepo = AppRepo(context)

    fun deleteTask(index: Int){
        repo.deleteTask(index)
    }



    fun editTask(index : Int, taskTitle:String, taskDec:String,isCheck:Boolean){

        repo.editTask(index,taskTitle,taskDec,isCheck )

        Log.e("checked","priority")

    }


}