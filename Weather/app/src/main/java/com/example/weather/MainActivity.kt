/*package com.example.weather

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.INTERNET
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.os.Bundle
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.core.app.ActivityCompat.requestPermissions
import com.example.weather.R.id
import com.example.weather.R.layout.activity_main


class MainActivity : AppCompatActivity(), LocationListener {
	lateinit var cityName: TextView

	//    lateinit var workerThread: Thread
	lateinit var currentLocation: Location

//    private lateinit var data: JSONObject

	//	@RequiresApi(R)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		cityName = findViewById(id.cityName)
		val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
		if (checkSelfPermission(
				this,
				ACCESS_FINE_LOCATION
			) != PERMISSION_GRANTED && checkSelfPermission(this, INTERNET) != PERMISSION_GRANTED
		) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, INTERNET), 0)
			return
		}
		locationManager.requestLocationUpdates(GPS_PROVIDER, 5000, 5F, this)
		currentLocation = Location(locationManager.getProvider(GPS_PROVIDER).toString())
		d("Location", currentLocation.toString())
		/* workerThread = Thread {
			 data =
				 Requester().doInBackground(URL("https://api.openweathermap.org/data/2.5/find?lat=${currentLocation.latitude}&lon=${currentLocation.longitude}&cnt=4&appid=cab8efea271fd15b90c0f63cc5fff952&units=imperial"))
		 }
		 workerThread.run()*/
		/*requestQueue = newRequestQueue(this);
		requestQueue.cancelAll(TAG)
		val request = getData()
		val jsonObject = JSONObject(JSONTokener)
		request.tag = TAG
		if (data.length() == 0) d("Uncaught Error", "Uncaught error/no response")
		d("json", data.toString())
		requestQueue.add(request)*/
//        d("json", data.toString())
	}

	/*@RequiresApi(R)
	fun getData(): JsonObjectRequest {
//		val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
		*//*if (checkSelfPermission(this, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return
		}
		val location :Location
		locationManager.getCurrentLocation(GPS_PROVIDER, null, this.mainExecutor, Consumer)*//*
		val currentLocation = Location(GPS_PROVIDER)
		val address = Geocoder(this).getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)[0]
		data = JSONObject()
		return JsonObjectRequest(
				"https://api.openweathermap.org/data/2.5/find?lat=${address.latitude}&lon=${address.longitude}&cnt=3&appid=cab8efea271fd15b90c0f63cc5fff952&units=imperial",
				null,
				{ data = JSONObject(JSONTokener(it.toString())) },
				{
					if (it != null) {
						e("Volley Error", it.stackTraceToString())
						makeText(this, "No Response from API", LENGTH_LONG).show()
					}
				}
		)
	}*/

	override fun onLocationChanged(location: Location) {
		currentLocation = location
//        workerThread.run()
	}


}

/*
class Requester : AsyncTask<URL, Int, JSONObject>() {
    private lateinit var bufferedReaderResult: String
    public override fun doInBackground(vararg params: URL): JSONObject {
        bufferedReaderResult = String()
        Thread {
            bufferedReaderResult =
                BufferedReader(InputStreamReader(params[0].openStream())).readLine()
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append(bufferedReaderResult)
        return JSONObject(stringBuilder.toString())
    }
}*/*/
package com.example.weather

import android.app.Activity
import android.graphics.Color.argb
import android.graphics.drawable.Drawable
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log.d
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R.id
import com.example.weather.R.id.*
import com.example.weather.R.layout.activity_main
import org.json.JSONObject
import java.net.URL
import java.time.LocalDateTime.ofEpochSecond
import java.time.ZoneId
import java.time.ZoneOffset.ofHours
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale.US

class MainActivity : AppCompatActivity() {

	companion object {
		const val API = "cab8efea271fd15b90c0f63cc5fff952"
		lateinit var location: String
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		location = "08810"
		val locationBar = findViewById<EditText>(addressBox).also {
			it.setHintTextColor(
				argb(
					127,
					255,
					255,
					255
				)
			)
		}
		findViewById<ImageButton>(imageButton).setOnClickListener {
			if (locationBar.text != null && locationBar.text.toString() != "") {
				location = locationBar.text.toString()
				locationBar.setText("")
			}
			WeatherTask(this).execute()
		}
		WeatherTask(this).execute()
	}

	class WeatherTask(private val activity: Activity) : AsyncTask<String, Void, String>() {
		var string: String = String()
		override fun doInBackground(vararg params: String): String? {
			val url =
				try {
					val geocoder = Geocoder(activity.applicationContext, US)
					val address = geocoder.getFromLocationName(location, 1)
					val lat = address[0].latitude
					val long = address[0].longitude
					d("data", "$lat $long")
					URL("https://api.openweathermap.org/data/2.5/find?lat=$lat&lon=$long&cnt=3&appid=$API&units=imperial").readText()
				} catch (e: Exception) {
					null
				}!!
			string = URL(
				"https://api.openweathermap.org/data/2.5/weather?lat=${
					JSONObject(url).getJSONArray("list").getJSONObject(0).getJSONObject("coord")
						.getDouble("lat")
				}&lon=${
					JSONObject(url).getJSONArray("list").getJSONObject(0).getJSONObject("coord")
						.getDouble("lon")
				}&appid=$API&units=imperial"
			).readText()
			return url
		}

		override fun onPostExecute(result: String) {
			super.onPostExecute(result)
			try {
				val jsonArray = JSONObject(result).getJSONArray("list")
				mainWeather(JSONObject(string))
				subWeather1(jsonArray.getJSONObject(1))
				subWeather2(jsonArray.getJSONObject(2))
			} catch (e: Exception) {
				e.printStackTrace()
			}

		}

		private fun formatTime(number: Long): String {
			val etFormat = ofPattern("M/d/yyyy h:mm:ss.S a zzz")
			val etZoneId = ZoneId.of("GMT-5")
			val x = ofEpochSecond(number, 0, ofHours(-5));
			val currentETime = x.atZone(etZoneId)
			return etFormat.format(currentETime)
		}

		private fun mainWeather(jsonObject: JSONObject) {
			val main = jsonObject.getJSONObject("main")
			val sys = jsonObject.getJSONObject("sys")
			val wind = jsonObject.getJSONObject("wind")
			val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
			val icon = weather.getString("icon")
			val updatedAtText = "Updated at: ${formatTime(jsonObject.getLong("dt"))}"
			val temp = main.getString("temp") + "°F"
			val tempMin = "Min Temp: " + main.getString("temp_min") + "°F"
			val tempMax = "Max Temp: " + main.getString("temp_max") + "°F"
			val pressure = main.getString("pressure")
			val humidity = main.getString("humidity")
			val sunrise = sys.getLong("sunrise")
			val sunset = sys.getLong("sunset")
			val windSpeed = wind.getString("speed")
			val weatherDescription = weather.getString("main")
			val address = jsonObject.getString("name") + ", " + sys.getString("country")
			activity.findViewById<TextView>(mainAddress).text = address
			activity.findViewById<TextView>(mainUpdated_at).text = updatedAtText
			activity.findViewById<TextView>(id.mainStatus).text =
				weatherDescription.capitalize(US)
			activity.findViewById<TextView>(id.temp).text = temp
			activity.findViewById<TextView>(temp_min).text = tempMin
			activity.findViewById<TextView>(temp_max).text = tempMax
			activity.findViewById<TextView>(id.sunrise).text = formatTime(sunrise)
			activity.findViewById<TextView>(id.sunset).text = formatTime(sunset)
			activity.findViewById<TextView>(id.wind).text = windSpeed
			activity.findViewById<TextView>(id.pressure).text = pressure
			activity.findViewById<TextView>(id.humidity).text = humidity
			activity.findViewById<ImageView>(mainStatusImage)
				.setImageDrawable(RetrieveImage().execute(icon).get())
		}

		private fun subWeather1(jsonObject: JSONObject) {
			val main = jsonObject.getJSONObject("main")
			val sys = jsonObject.getJSONObject("sys")
			val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
			val icon = weather.getString("icon")
			val temp = main.getString("temp") + "°F"
			val weatherDescription = weather.getString("main")
			val address = jsonObject.getString("name") + ", " + sys.getString("country")
			activity.findViewById<TextView>(subAddress1).text = address
			activity.findViewById<TextView>(subStatus1).text = weatherDescription.capitalize(US)
			activity.findViewById<TextView>(id.subTemp1).text = temp
			activity.findViewById<ImageView>(subStatusImage1)
				.setImageDrawable(RetrieveImage().execute(icon).get())
		}

		private fun subWeather2(jsonObject: JSONObject) {
			val main = jsonObject.getJSONObject("main")
			val sys = jsonObject.getJSONObject("sys")
			val weather = jsonObject.getJSONArray("weather").getJSONObject(0)
			val icon = weather.getString("icon")
			val temp = main.getString("temp") + "°F"
			val weatherDescription = weather.getString("main")
			val address = jsonObject.getString("name") + ", " + sys.getString("country")
			activity.findViewById<TextView>(subAddress2).text = address
			activity.findViewById<TextView>(subStatus2).text = weatherDescription.capitalize(US)
			activity.findViewById<TextView>(id.subTemp2).text = temp
			activity.findViewById<ImageView>(subStatusImage2)
				.setImageDrawable(RetrieveImage().execute(icon).get())
		}
	}

	class RetrieveImage() : AsyncTask<String, Void, Drawable>() {
		override fun doInBackground(vararg params: String?): Drawable {
			return Drawable.createFromStream(
				URL("https://openweathermap.org/img/wn/${params[0]}.png").openStream(),
				"src"
			)
		}
	}
}