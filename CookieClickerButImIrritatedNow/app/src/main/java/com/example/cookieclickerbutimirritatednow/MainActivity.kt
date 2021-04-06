package com.example.cookieclickerbutimirritatednow

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cookieclickerbutimirritatednow.ui.cookie.CookieFragment
import kotlin.math.truncate
import com.example.cookieclickerbutimirritatednow.R.id.nav_host_fragment as nav_host
import com.example.cookieclickerbutimirritatednow.R.id.nav_view as nav
import com.example.cookieclickerbutimirritatednow.R.id.navigation_buildings as buildingNav
import com.example.cookieclickerbutimirritatednow.R.id.navigation_cookie as cookieNav
import com.example.cookieclickerbutimirritatednow.R.layout.activity_main as main
import com.google.android.material.bottomnavigation.BottomNavigationView as BNV
import java.util.concurrent.atomic.AtomicBoolean as AtBool
import java.util.concurrent.atomic.AtomicReference as AtRef

internal class MainActivity : AppCompatActivity() {
	companion object {
		/*val storage = Firebase.storage.reference
		var jsonObject = JSONObject()*/
		var cookie_quantity: AtRef<Double> = AtRef(0.0)
		var cps: AtRef<Double> = AtRef(0.0)
		var isCpSOccurring = AtBool()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(main)
		findViewById<BNV>(nav).setupWithNavController(findNavController(nav_host).also {
			setupActionBarWithNavController(
				it,
				AppBarConfiguration(setOf(cookieNav, buildingNav))
			)
		})
		val handler = Handler(mainLooper)
		handler.post(object : Thread() {
			override fun run() {
				isCpSOccurring.getAndSet(true)
				cookie_quantity.getAndUpdate { it + cps.get() }
				val temp = truncate(cookie_quantity.get()).toString()
				CookieFragment.cookiesTextView.get().text = temp.substring(0, temp.indexOf('.'))
				isCpSOccurring.getAndSet(false)
				handler.postDelayed(this, 1000)
			}
		})
	}
}