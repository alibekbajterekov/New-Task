package com.example.myapplication.room






import androidx.room.RoomDatabase
import com.example.myapplication.ui.home.TaskModel


@androidx.room.Database(entities =[TaskModel::class], version = 1 )
abstract class Database: RoomDatabase() {
    abstract fun taskDao(): TaskDao?
}
