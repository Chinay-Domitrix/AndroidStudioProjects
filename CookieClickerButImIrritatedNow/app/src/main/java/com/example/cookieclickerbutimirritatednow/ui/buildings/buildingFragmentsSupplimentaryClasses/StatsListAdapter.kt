package com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.View
import androidx.annotation.LayoutRes
import com.example.cookieclickerbutimirritatednow.R.id.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.Objects.*
import android.view.LayoutInflater as LI
import android.view.ViewGroup as VG
import android.widget.ArrayAdapter as AA
import android.widget.ImageView as IV
import android.widget.TextView as TV
import com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses.BuildingStats as Stats

class StatsListAdapter(
	context: Context,
	@LayoutRes private val resource: Int,
	private val objects: List<Stats>
) : AA<Stats>(context, resource, objects) {
	override fun getView(position: Int, convertView: View?, parent: VG): View {
		val view =
			(requireNonNull(context)
				.getSystemService(LAYOUT_INFLATER_SERVICE) as LI).inflate(
				resource,
				null
			)
		objects[position].run {
			view.run {
				findViewById<IV>(adapter_stats_building_icon).setImageResource(imageID)
				findViewById<TV>(adapter_stats_building_name).text = name
				"CpS per Building: $cps".also { findViewById<TV>(adapter_stats_unit_cps).text = it }
				"Current Total CpS: ${cps * quantity}".also {
					findViewById<TV>(
						adapter_stats_max_cps
					).text = it
				}
			}
		}
		notifyDataSetChanged()
		return view
	}
	fun toJSONObject() = JSONObject().apply {
		put("Context", context)
		put("Layout ID", resource)
		put("Buy Buttons", JSONArray(objects))
	}

	override fun toString() = JSONObject().apply {
		put("Context", context)
		put("Layout ID", resource)
		put("Buy Buttons", JSONArray(objects))
	}.toString()
}