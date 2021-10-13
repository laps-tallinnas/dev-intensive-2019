package ru.skillbranch.devintensive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val TAG:String  = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate activated")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart activated")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume activated")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart activated")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause activated")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy activated")
    }
}