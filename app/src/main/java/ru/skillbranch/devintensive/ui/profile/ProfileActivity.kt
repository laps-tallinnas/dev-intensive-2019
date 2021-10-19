package ru.skillbranch.devintensive.ui.profile

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel


class ProfileActivity : AppCompatActivity(){
    private lateinit var viewModel: ProfileViewModel
    private val TAG:String  = "MainActivity"
    companion object {
        const val IS_EDIT_MODE ="IS_EDIT_MODE"
    }
    var btn_edit: ImageButton? = null
    var btn_switch :ImageButton? = null
    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate activated")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        btn_edit = ib_edit
        btn_switch = ib_switch
        initViews(savedInstanceState)
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

    private fun initViews(savedInstanceState: Bundle?){
        viewFields = mapOf("nickname" to et1, "home" to et2)
        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?:false

        btn_edit?.setOnClickListener(View.OnClickListener {
            if (isEditMode) saveProfileInfo()
            isEditMode = isEditMode.not()
            showCurrentMode(isEditMode) })

    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
    }

    private fun updateUI(profile: Profile?) {
        profile?.toMap().also{
            for ((k,v) in viewFields) {
                v.text = it?.get(k).toString()
        }
        }
    }

    private fun showCurrentMode(isEdit: Boolean){
        val info = viewFields.filter { setOf("nickname", "home").contains(it.key) }
        for ((_,v) in info) {
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha  = if (isEdit) 255 else 0
        }
    }

    // After 28 API this method is called after OnStop
    // Not called if Activity will be closed by user.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun saveProfileInfo (){
        Profile (
            name = et1.text.toString(),
            address = et2.text.toString()
                ).apply{
            viewModel.saveProfileData(this)
        }
    }


}