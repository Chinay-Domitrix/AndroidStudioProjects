package com.example.cookieclickerbutimirritatednow.ui.cookie

import android.annotation.SuppressLint
import android.graphics.Color.rgb
import android.os.Bundle
import android.util.Log.d
import android.view.Gravity.CENTER
import android.view.View
import android.view.View.generateViewId
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.Animation.RELATIVE_TO_SELF
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.fragment.app.Fragment
import com.example.cookieclickerbutimirritatednow.MainActivity
import com.example.cookieclickerbutimirritatednow.MainActivity.Companion.cookie_quantity
import com.example.cookieclickerbutimirritatednow.MainActivity.Companion.isCpSOccurring
import com.example.cookieclickerbutimirritatednow.R.id.*
import com.example.cookieclickerbutimirritatednow.R.layout.fragment_cookie
import com.example.cookieclickerbutimirritatednow.R.string.plus_one
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.math.round
import kotlin.math.truncate
import kotlin.random.Random.Default.nextDouble
import android.app.Activity as Act
import android.view.LayoutInflater as LI
import android.view.ViewGroup as VG
import android.view.animation.AlphaAnimation as FadeAnim
import android.view.animation.AnimationSet as AnimSet
import android.view.animation.ScaleAnimation as Scale
import android.view.animation.TranslateAnimation as ShiftAnim
import android.widget.ImageView as IV
import android.widget.TextView as TV
import androidx.constraintlayout.widget.ConstraintLayout as CL
import java.util.concurrent.atomic.AtomicReference as AtRef


internal class CookieFragment : Fragment() {
	companion object {
		var cookiesTextView = AtRef<TV>()
		var cpsQuantity = AtRef<TV>()
	}

	@SuppressLint("ClickableViewAccessibility")
	override fun onCreateView(
		inflater: LI,
		container: VG?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(fragment_cookie, container, false)
		root.run {
			cookiesTextView.set(findViewById(cookie_quant))
			thread {
				val perfectCookie = findViewById<IV>(perfect_cookie)
				cookiesTextView = AtRef(findViewById(cookie_quant))
				cpsQuantity = AtRef(findViewById(cps_quant))
				val cookieLayout = findViewById<CL>(cookie_layout)
				var floatArray = FloatArray(2)
				perfectCookie.setOnTouchListener { _, event ->
					floatArray = floatArrayOf(event.x, event.y)
					false
				}
				perfectCookie.setOnClickListener {
					val additionTextView = TV(context)
					additionTextView.run {
						id = generateViewId()
						gravity = CENTER
						text = context.getString(plus_one)
						textSize = 25f
						setTextColor(rgb(242, 242, 242))
						x = floatArray[0] + nextDouble((-5).toDouble(), 6.toDouble()).toFloat()
						y =
							floatArray[1] + (context as Act).windowManager.currentWindowMetrics.bounds.height() / 8
						layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
					}
					ConstraintSet().run {
						clone(cookieLayout)
						arrayOf(LEFT, RIGHT, TOP, BOTTOM).forEach { i ->
							connect(additionTextView.id, i, perfect_cookie, i)
						}
						applyTo(cookieLayout)
					}
					cookieLayout.addView(additionTextView)
					/*val alphaAnimation = AlphaAnimation(1f, 0f)
					alphaAnimation.duration = 1500
					alphaAnimation.fillAfter = true
					val translateAnimation = TranslateAnimation(0f, 0f, 0f, -50f)
					translateAnimation.duration = 1500
					translateAnimation.fillAfter = true
					additionTextView.startAnimation(translateAnimation)
					additionTextView.startAnimation(alphaAnimation)*/
					additionTextView.startAnimation(AnimSet(false).apply {
						addAnimation(FadeAnim(1f, 0f))
						addAnimation(ShiftAnim(0f, 0f, 0f, -16f))
					}.apply {
						duration = 1500
						fillAfter = true
					})
					if (isCpSOccurring.get()) sleep(10)
					cookie_quantity.getAndUpdate { it + 1 }
					val temp = truncate(cookie_quantity.get()).toString()
					cookiesTextView.get().text = temp.substring(0, temp.indexOf('.'))
					cpsQuantity.get().text = "${round(MainActivity.cps.get() * 10) / 10.0}"
					perfectCookie.startAnimation(
						Scale(
							1f,
							.95f,
							1f,
							.95f,
							RELATIVE_TO_SELF,
							.5f,
							RELATIVE_TO_SELF,
							.5f
						).apply { duration = 100 })
					d("quant", cookie_quantity.toString())
				}
			}
		}
		return root
	}
}