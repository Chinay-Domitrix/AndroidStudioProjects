package com.example.cookie_clicker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cookie_clicker.R.id.*
import com.example.cookie_clicker.R.layout.activity_main
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.atomic.*


class MainActivity : AppCompatActivity() {
	companion object {
//		val storage = Firebase.storage.reference
//		var jsonObject = JSONObject()
		var cookie_quantity: AtomicReference<Double> = AtomicReference(0.0)
		var cps: AtomicReference<Double> = AtomicReference(0.0)
		var isCpSOccurring = AtomicBoolean()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
//		val localFile = createTempFile(
//			"cookie_clicker",
//			"json",
//			File("src\\main\\java\\com\\example\\cookie_clicker")
//		)
//		storage.child("/cookie_clicker.json").getFile(localFile)
//		val json: String
//		val inputStream = applicationContext.assets.open("file_name.json")
//		val buffer = ByteArray(inputStream.available())
//		inputStream.read(buffer)
//		inputStream.close()
//		json = String(buffer, defaultCharset())
//		jsonObject = JSONObject(json)
//		localFile.delete()
		val navController = findNavController(nav_host_fragment)
		setupActionBarWithNavController(
			navController,
			AppBarConfiguration(setOf(navigation_cookie, navigation_buildings))
		)
//		cookie_quantity.set(jsonObject.getDouble("cookies"))
//		val jsonArray = jsonObject.getJSONArray("building_quantities")
//		(0..jsonArray.length()).forEach()
		findViewById<BottomNavigationView>(nav_view).setupWithNavController(navController)
//		val newLooper = Looper.
		/*val handler = Handler(mainLooper)
		val runnableCode: Runnable = object : Thread() {
			override fun run() {
				isCpSOccurring.getAndSet(true)
				cookie_quantity.getAndUpdate { it + cps.get() }
				val temp = truncate(cookie_quantity.get()).toString()
				CookieFragment.cookiesTextView.get().text = temp.substring(0, temp.indexOf('.'))
				isCpSOccurring.getAndSet(false)
				handler.postDelayed(this, 1000)
			}
		}
		handler.post(runnableCode)*/
	}

//	override fun onStop() {
//		super.onStop()
//		val jsonToPush = JSONObject()
//		jsonToPush.put("cookies", cookie_quantity.get())
//		jsonToPush.put("cps", cps.get())
//		val list = arrayListOf<JSONObject>()
//		(0..17).forEach { i ->
//			list += JSONObject().put(
//				BuildingFragment.buyButtons[i].name.toString().replace(" ".toRegex(), "_")
//					.toLowerCase(ROOT) + "_quantity", BuildingFragment.buildingQuantities[i]
//			)
//		}
//		jsonToPush.put("building_quantities", JSONArray(list))
//		val localFile = createTempFile(
//			"cookie_clicker",
//			"json",
//			File("src\\main\\java\\com\\example\\cookie_clicker")
//		)
//		FileWriter(localFile).append(jsonToPush.toString())
//		storage.putFile(Uri.fromFile(localFile))
//	}
}