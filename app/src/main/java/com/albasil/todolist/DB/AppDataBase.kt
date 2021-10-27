package com.albasil.todolist.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.albasil.todolist.TaskDao


@Database(entities = [DataTask::class],version = 1)
abstract class AppDataBase :RoomDatabase(){

    abstract val taskDao: TaskDao

    companion object{

        private var INSTANCE: AppDataBase? = null

        fun getAppDataBase(context: Context): AppDataBase?{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app-database"
                ).build()
            }
            return INSTANCE

        }

    }



}