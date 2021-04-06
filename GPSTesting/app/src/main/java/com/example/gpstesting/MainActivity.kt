@file:Suppress("DEPRECATION")

package com.example.gpstesting

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.*
import android.location.LocationManager.GPS_PROVIDER
import android.os.AsyncTask
import android.os.Build.VERSION_CODES.Q
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.core.app.ActivityCompat.requestPermissions
import com.example.gpstesting.R.id
import com.example.gpstesting.R.id.textView
import com.example.gpstesting.R.layout.activity_main
import java.lang.System.currentTimeMillis
import java.lang.Thread.sleep
import java.util.Locale.*
import kotlin.math.roundToInt

@RequiresApi(Q)
class MainActivity : AppCompatActivity() {
	private lateinit var listener: LocationListener
	private lateinit var loc: LocationManager
	private var previousLocation: Location? = null
	private lateinit var textView1: TextView
	private lateinit var textView2: TextView
	private lateinit var textView3: TextView
	private lateinit var textView4: TextView
	private lateinit var lat: String
	private lateinit var long: String
	private var totalDistance = 0.0
	private var startTime = 0.0
	private val mainActivity = this

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		loc = getSystemService(LOCATION_SERVICE) as LocationManager
		textView1 = findViewById(textView)
		textView2 = findViewById(id.textView2)
		textView3 = findViewById(id.textView3)
		textView4 = findViewById(id.textView4)
		totalDistance = 0.0
		startTime = currentTimeMillis() / 1000.0
		listener = LocationListener {
			lat = it.latitude.toString()
			long = it.longitude.toString()
			textView1.text = StringBuilder("Coordinates: ($lat, $long)")
			FetchLocation(mainActivity).execute(it.latitude, it.longitude)
			if (previousLocation != null) {
				val div = previousLocation?.distanceTo(it)?.roundToInt()?.toDouble()?.div(1000)
				totalDistance += if (div != null) div else throw KotlinNullPointerException()
				textView3.text = StringBuilder("Approximate Distance Traveled: ${totalDistance.times(100).roundToInt().toDouble().div(100)} miles")
				textView4.text = StringBuilder("Time Taken: ${currentTimeMillis().div(1000.0).minus(startTime).times(1000.0).roundToInt().div(1000.0)} seconds")
			}
			previousLocation = it
		}
		if (checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 1)
		if (checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) loc.requestLocationUpdates(GPS_PROVIDER, 5000, 10f, listener) else {
			makeText(this, "The app doesn't work without location permissions!", LENGTH_LONG).show()
			requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 1)
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
			if (checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), requestCode)
			if (checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
				startTime = currentTimeMillis().div(1000.0)
				loc.requestLocationUpdates(GPS_PROVIDER, 5000, 10F, listener)
			} else {
				makeText(this, "The app doesn't work without location permissions!", LENGTH_LONG).show()
				sleep(2000)
				requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 1)
			}
		}
		return
	}

	class FetchLocation(mainActivity: MainActivity) : AsyncTask<Double?, Void?, Address?>() {
		private val mainActivity by lazy { mainActivity }
		override fun doInBackground(vararg params: Double?): Address? = Geocoder(mainActivity, getDefault()).getFromLocation(params[0]!!, params[1]!!, 1)[0]

		override fun onPostExecute(result: Address?) {
			result?.getAddressLine(0).also { mainActivity.textView2.text = it }
		}
	}
}