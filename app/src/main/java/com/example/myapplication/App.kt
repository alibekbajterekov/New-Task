package com.example.myapplication

import android.app.Application
import androidx.room.Room

import com.example.myapplication.room.Database

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, Database::class.java,
            "database")
            .allowMainThreadQueries()
                .build()

    }
    companion object{
        lateinit var database: Database
    }

}