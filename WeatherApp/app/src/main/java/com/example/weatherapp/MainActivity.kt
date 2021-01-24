package com.example.weatherapp

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable.createFromStream
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R.id
import com.example.weatherapp.R.id.*
import org.json.JSONObject
import java.lang.Thread.sleep
import java.net.URL
import java.time.LocalDateTime.ofEpochSecond
import java.time.ZoneId
import java.time.ZoneOffset.ofHours
import java.time.format.DateTimeFormatter.ofPattern
import java.util.Locale.ENGLISH

class MainActivity : AppCompatActivity() {

	companion object {
		const val API = "cab8efea271fd15b90c0f63cc5fff952"
		lateinit var location: String
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val locationBar = findViewById<EditText>(addressBox)
		locationBar.setHintTextColor(Color.argb(127, 255, 255, 255))
		while (locationBar.text == null && locationBar.text.toString() == "") findViewById<ImageButton>(
			imageButton
		).setOnClickListener {
			if (!(locationBar.text == null && locationBar.text.toString() == "")) location =
				locationBar.text.toString()
		}
		while (true) {
			WeatherTask(this).execute()
			sleep(60000)
		}
	}

}

class WeatherTask(private val activity: Activity) : AsyncTask<String, Void, String>() {

	override fun doInBackground(vararg params: String): String? {
		val geocoder =
			Geocoder(activity.applicationContext).getFromLocationName(MainActivity.location, 1)[0]
		return try {
			URL("https://api.openweathermap.org/data/2.5/find?lat=${geocoder.latitude}&lon=${geocoder.longitude}&cnt=3&appid=${MainActivity.API}&units=imperial").readText()
		} catch (e: Exception) {
			null
		}
	}

	override fun onPostExecute(result: String) {
		super.onPostExecute(result)
		try {
			/* Extracting JSON returns from the API */
			val jsonArray = JSONObject(result).getJSONArray("list")
			mainWeather(jsonArray.getJSONObject(0))
			/* Views populated, Hiding the loader, Showing the main design */
			/*activity.findViewById<ProgressBar>(loader).visibility = GONE
			activity.findViewById<RelativeLayout>(mainContainer).visibility = VISIBLE*/

		} catch (e: Exception) {
			e.printStackTrace()
			/*activity.findViewById<ProgressBar>(loader).visibility = GONE
			activity.findViewById<TextView>(errorText).visibility = VISIBLE*/
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
		val updatedAt = jsonObject.getLong("dt")
		val icon = jsonObject.getString("icon")
		val updatedAtText = "Updated at: ${formatTime(updatedAt)}"
		val temp = main.getString("temp") + "°F"
		val tempMin = "Min Temp: " + main.getString("temp_min") + "°F"
		val tempMax = "Max Temp: " + main.getString("temp_max") + "°F"
		val pressure = main.getString("pressure")
		val humidity = main.getString("humidity")
		val sunrise = sys.getLong("sunrise")
		val sunset = sys.getLong("sunset")
		val windSpeed = wind.getString("speed")
		val weatherDescription = weather.getString("description")
		val address = jsonObject.getString("name") + ", " + sys.getString("country")
		activity.findViewById<TextView>(mainAddress).text = address
		activity.findViewById<TextView>(mainUpdated_at).text = updatedAtText
		activity.findViewById<TextView>(id.mainStatus).text = weatherDescription.capitalize(ENGLISH)
		activity.findViewById<TextView>(id.temp).text = temp
		activity.findViewById<TextView>(temp_min).text = tempMin
		activity.findViewById<TextView>(temp_max).text = tempMax
		activity.findViewById<TextView>(id.sunrise).text = formatTime(sunrise)
		activity.findViewById<TextView>(id.sunset).text = formatTime(sunset)
		activity.findViewById<TextView>(id.wind).text = windSpeed
		activity.findViewById<TextView>(id.pressure).text = pressure
		activity.findViewById<TextView>(id.humidity).text = humidity
		activity.findViewById<ImageView>(mainStatusImage).setImageDrawable(
			createFromStream(
				URL("https://openweathermap.org/img/wn/$icon@2x.png").openStream(),
				"src"
			)
		)
	}
}
