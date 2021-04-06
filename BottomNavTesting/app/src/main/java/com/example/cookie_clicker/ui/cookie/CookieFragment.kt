package com.example.cookie_clicker.ui.cookie

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color.rgb
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AlphaAnimation
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.fragment.app.Fragment
import com.example.cookie_clicker.MainActivity.Companion.cookie_quantity
import com.example.cookie_clicker.MainActivity.Companion.isCpSOccurring
import com.example.cookie_clicker.R.id.*
import com.example.cookie_clicker.R.layout.fragment_cookie
import com.example.cookie_clicker.R.string.plus_one
import java.lang.Thread.sleep
import java.util.concurrent.atomic.*


class CookieFragment : Fragment() {
	companion object {
		var cookiesTextView: AtomicReference<TextView> = AtomicReference()
	}

	@SuppressLint("ClickableViewAccessibility")
	@RequiresApi(R)
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(fragment_cookie, container, false)
		cookiesTextView.set(root.findViewById(cookie_quant))
		Thread {
			val perfectCookie = root.findViewById<ImageView>(perfect_cookie)
			val cookieAmount = root.findViewById<TextView>(cookie_quant)
			val cookieLayout = root.findViewById<ConstraintLayout>(cookie_layout)
			var floatArray = FloatArray(2)
			perfectCookie.setOnTouchListener { _, event ->
				floatArray = floatArrayOf(event.x, event.y)
				false
			}
			perfectCookie.setOnClickListener {
				val additionTextView = TextView(root.context)
				additionTextView.run {
					id = generateViewId()
					gravity = CENTER
					text = context.getString(plus_one)
					textSize = 25f
					setTextColor(rgb(242, 242, 242))
					x = floatArray[0]
					y =
						floatArray[1] + (root.context as Activity).windowManager.currentWindowMetrics.bounds.height() / 8
					layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
				}
				val constraintSet = ConstraintSet()
				constraintSet.run {
					clone(cookieLayout)
					connect(additionTextView.id, BOTTOM, perfect_cookie, BOTTOM)
					connect(additionTextView.id, TOP, perfect_cookie, TOP)
					connect(additionTextView.id, LEFT, perfect_cookie, LEFT)
					connect(additionTextView.id, RIGHT, perfect_cookie, RIGHT)
					applyTo(cookieLayout)
				}
				/*val windowMetrics = (root.context as Activity).windowManager.currentWindowMetrics
				val display = windowMetrics.bounds*/
				cookieLayout.addView(additionTextView)
				val alphaAnimation = AlphaAnimation(1f, 0f)
				alphaAnimation.duration = 1500
				alphaAnimation.fillAfter = true
				additionTextView.startAnimation(alphaAnimation)
				if (isCpSOccurring.get()) sleep(10)
				cookie_quantity.getAndUpdate { it + 1 }
//				val temp = truncate(cookie_quantity.get()).toString()
//				cookiesTextView.get().text = temp.substring(0, temp.indexOf('.'))
				val imageAnimate =
					ScaleAnimation(1f, .95f, 1f, .95f, RELATIVE_TO_SELF, .5f, RELATIVE_TO_SELF, .5f)
				imageAnimate.duration = 100
				perfectCookie.startAnimation(imageAnimate)
				d("quant", cookie_quantity.toString())
			}
		}.run()
		return root
	}
}