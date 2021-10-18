package ru.skillbranch.devintensive.ui.profile

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender


class ProfileActivity : AppCompatActivity(){
    private val TAG:String  = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate activated")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
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


    // After 28 API this method is called after OnStop
    // Not called if Activity will be closed by user.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}