package com.example.intentsdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.intentsdemo.R.id.*
import com.example.intentsdemo.R.layout.activity_number


class Number : AppCompatActivity() {
	private lateinit var sentValue: TextView
	private lateinit var editText: EditText
	lateinit var finish: Button
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_number)
		sentValue = findViewById(sentTextView_number)
		editText = findViewById(editTextNumber_number)
		finish = findViewById(id_done_button)
		sentValue.text = intent.getStringExtra("KEY001").toString()
		finish.setOnClickListener {
			val sendInfoBack = Intent()
			sendInfoBack.putExtra(MainActivity.INTENT_CODE, editText.text.toString())
			setResult(RESULT_OK, sendInfoBack)
			finish()
		}
	}
}