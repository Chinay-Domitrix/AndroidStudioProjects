package com.example.cookieclicker_bottomnavigationversion2

import android.graphics.Color.rgb
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity.*
import android.view.View.generateViewId
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AlphaAnimation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.cookieclicker_bottomnavigationversion2.R.id.*
import com.example.cookieclicker_bottomnavigationversion2.R.layout.activity_main
import com.example.cookieclicker_bottomnavigationversion2.R.string.plus_one
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.math.MathContext
import java.math.RoundingMode.DOWN
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

class MainActivity : AppCompatActivity() {
	companion object {
		var cookie_quantity: AtomicReference<Double> = AtomicReference(0.0)
		var cps: AtomicReference<Double> = AtomicReference(0.0)
	}

	@RequiresApi(R)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		val perfectCookie = findViewById<ImageView>(perfect_cookie)
		val cookieAmount = findViewById<TextView>(cookie_quant)
		val cookieLayout = findViewById<ConstraintLayout>(layout_cookie)
		val floatArray = FloatArray(2)
		perfectCookie.setOnTouchListener { _, event ->
			floatArray[0] = event.getX()
			floatArray[1] = event.getY()
			false
		}
		perfectCookie.setOnClickListener {
			val additionTextView = TextView(cookieLayout.context)
			additionTextView.run {
				id = generateViewId()
				gravity = CENTER
				text = context.getString(plus_one)
				textSize = 25f
				setTextColor(rgb(242, 242, 242))
				x = floatArray[0]
				y = floatArray[1] + windowManager.currentWindowMetrics.bounds.height() / 8
				layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
			}
			val constraintSet = ConstraintSet()
			constraintSet.run {
				clone(cookieLayout)
				connect(additionTextView.id, BOTTOM, perfect_cookie, BOTTOM)
				connect(additionTextView.id, TOP, perfect_cookie, TOP)
				connect(additionTextView.id, START, perfect_cookie, START)
				connect(additionTextView.id, END, perfect_cookie, END)
				applyTo(cookieLayout)
			}
			/*val windowMetrics = (root.context as Activity).windowManager.currentWindowMetrics
			val display = windowMetrics.bounds*/
			cookieLayout.addView(additionTextView)
			val alphaAnimation = AlphaAnimation(1f, 0f)
			alphaAnimation.run {
				duration = 1500
				fillAfter = true
			}
			additionTextView.startAnimation(alphaAnimation)
			cookie_quantity.get().run {
				inc()
				round(MathContext(1, DOWN)).toBigIntegerExact().toString().also { cookieAmount.text = it }
			}
			val imageAnimate = ScaleAnimation(1f, .95f, 1f, .95f, RELATIVE_TO_SELF, .5f, RELATIVE_TO_SELF, .5f)
			imageAnimate.duration = 100
			perfectCookie.startAnimation(imageAnimate)
			d("quant", cookie_quantity.toString())
		}
	}
}