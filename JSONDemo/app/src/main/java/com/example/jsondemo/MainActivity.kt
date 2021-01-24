package com.example.jsondemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jsondemo.R.layout.activity_main
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		/*val schoolInfo = JSONObject()
		try {
			schoolInfo.put("name", "Chirag Baviskar").put("grade", 11).put("id", 30000054)
		} catch (e: JSONException) {
			e.printStackTrace()
		}
		val compSciClass = JSONObject()
		try {
			compSciClass.put("compscigrade", "A").put("compscipercent", 95)
			schoolInfo.put("comp sci class", compSciClass)
		} catch (e: JSONException) {
			e.printStackTrace()
		}
		val bioClass = JSONObject()
		try {
			bioClass.put("biograde", "A-").put("biopercent", 90)
			schoolInfo.put("bio class", compSciClass)
		} catch (e: JSONException) {
			e.printStackTrace()
		}
		d("TAG", schoolInfo.toString())
		try {
			d("RETRIEVE", String.format("%s (%s)", schoolInfo.getJSONObject("bio class").getString("biograde"), schoolInfo.getJSONObject("bio class").getString("biopercent")))
		} catch (e: JSONException) {
			e.printStackTrace()
		}*/
		val music = JSONObject();
		val stringList = ArrayList(listOf("Mark Ronson Featuring Bruno Mars", "Uptown Funk", "LMFAO Featuring Lauren Bennett & GoonRock", "Party Rock Anthem", "Ed Sheeran", "Shape Of You", "The Chainsmokers Featuring Halsey", "Closer", "Maroon 5 Featuring Cardi B", "Girls Like You"))
		var i = 0
		while (i < stringList.size) {
			try {
				music.put("value $i", JSONObject().put("artist $i", stringList[i]).put("song $i", stringList[i + 1]))
			} catch (e: JSONException) {
				e.printStackTrace()
			}
			i += 2
		}
	}
}