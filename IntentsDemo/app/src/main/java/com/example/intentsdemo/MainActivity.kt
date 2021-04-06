package com.example.intentsdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.intentsdemo.R.id.returnTextView
import com.example.intentsdemo.R.id.startActivityButton
import com.example.intentsdemo.R.layout.activity_main


class MainActivity : AppCompatActivity() {
	private lateinit var startButton: Button
	private lateinit var returnText: TextView

	companion object {
		const val RETURN_VALUE = 1
		const val INTENT_CODE = "KEY_1"
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == RETURN_VALUE && resultCode == RESULT_OK) returnText.text = data?.getStringExtra(INTENT_CODE)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		startButton = findViewById(startActivityButton)
		returnText = findViewById(returnTextView)
		startButton.setOnClickListener {
			val intent = Intent(baseContext, Number::class.java)
			intent.putExtra("TEST", "This is a test")
			startActivityForResult(intent, RETURN_VALUE)
		}
	}
}