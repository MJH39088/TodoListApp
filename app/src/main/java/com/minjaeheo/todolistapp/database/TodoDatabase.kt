package com.minjaeheo.todolistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minjaeheo.todolistapp.model.TodoInfo

@Database(entities = [TodoInfo::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao

    companion object {
        private var instance: TodoDatabase? = null
        @Synchronized
        fun getInstance(context: Context) : TodoDatabase? {
            if (instance == null) {
                synchronized(TodoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo-database"
                    ).build()
                }
            }
            return instance
        }
    }
}