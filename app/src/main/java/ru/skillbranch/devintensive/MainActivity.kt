package ru.skillbranch.devintensive

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
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG:String  = "MainActivity"
    lateinit var benderImage : ImageView
    lateinit var textTxt:TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj :Bender
    lateinit var saved_text: String
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate activated")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        benderImage = iv_bender
        textTxt = tv_text
        saved_text = savedInstanceState?.getString("TEXT") ?: ""
        messageEt = et_message
        messageEt.setText(saved_text)
        sendBtn = iv_send
        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.PROFESSION.name
        Log.d(TAG, "status: $status, question:$question")
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.setText(benderObj.askQuestion())
        sendBtn.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send){
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.setText(phrase)
        }

    }
    // After 28 API this method is called after OnStop
    // Not called if Activity will be closed by user.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        outState?.putString("TEXT", et_message.text.toString())
        Log.d(TAG, "called OnSaveInstanceState ${benderObj.status.name}, ${benderObj.question.name}")
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}