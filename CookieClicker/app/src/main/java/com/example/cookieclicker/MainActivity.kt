package com.example.cookieclicker

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.cookieclicker.R.id.*
import com.example.cookieclicker.R.layout.activity_main
import java.math.BigInteger
import java.math.BigInteger.ZERO

class MainActivity : AppCompatActivity() {
	var numCookies: BigInteger = ZERO
	val perfectCookie = findViewById<ImageView>(perfect_cookie)
	val cookieLayout = findViewById<LinearLayout>(layout_cookie)
	val buildingLayout = findViewById<LinearLayout>(layout_buildings)
	val cookieNavButton = findViewById<Button>(navButtonCookie)
	val buildingsNavButton = findViewById<Button>(navButtonBuildings)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		cookieNavButton.setOnClickListener {
			cookieLayout.visibility = VISIBLE
			buildingLayout.visibility = GONE
		}
		buildingsNavButton.setOnClickListener {
			buildingLayout.visibility = VISIBLE
			cookieLayout.visibility = GONE
		}
	}
}