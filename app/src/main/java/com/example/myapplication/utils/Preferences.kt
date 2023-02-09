package com.example.myapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.R

class Preferences (context: Context) {
 private val sharedPreference: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun setBoardingShowed(isShow: Boolean){
        return sharedPreference.edit().putBoolean("board", isShow).apply()
    }
    fun isBoardingShowed(): Boolean{
        return sharedPreference.getBoolean("board", false)
    }

    fun setImageProfile(uri: String){
        return sharedPreference.edit().putString("image", uri).apply()
    }
    fun getImageProfile(): String?{
        return sharedPreference.getString("image", R.mipmap.ic_launcher_round.toString())
    }
    fun saveName(name: String){
      sharedPreference.edit().putString("name", name).apply()
    }
    fun getName(): String{
        return sharedPreference.getString("name","").toString()
    }
    fun saveAge(age:String){
        sharedPreference.edit().putString("AGE_KEY",age).apply()
    }
    fun getAge():String{
        return sharedPreference.getString("AGE_KEY","").toString()
    }
    fun saveFloor(floor: String){
        sharedPreference.edit().putString("FLOOR_KEY",floor).apply()
    }
    fun getFloor():String {
        return sharedPreference.getString("FLOOR_KEY", "").toString()
    }

    companion object{
        private const val NAME_KEY = "name.preference"
    }
}