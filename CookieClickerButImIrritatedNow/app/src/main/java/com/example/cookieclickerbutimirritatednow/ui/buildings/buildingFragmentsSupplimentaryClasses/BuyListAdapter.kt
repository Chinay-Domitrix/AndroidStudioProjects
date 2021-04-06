package com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.View
import com.example.cookieclickerbutimirritatednow.R.id.adapter_building_buy_tile
import com.example.cookieclickerbutimirritatednow.ui.buildings.BuildingFragment.Companion.buildingQuantities
import com.example.cookieclickerbutimirritatednow.ui.cookie.CookieFragment
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Integer.valueOf
import java.util.Objects.*
import android.view.LayoutInflater as LI
import android.view.ViewGroup as VG
import android.widget.ArrayAdapter as AA
import android.widget.ImageView as IV
import android.widget.TextView as TV
import androidx.constraintlayout.widget.ConstraintLayout as CL
import com.example.cookieclickerbutimirritatednow.MainActivity.Companion as Main
import com.example.cookieclickerbutimirritatednow.R.id.adapter_building_cost as building_cost
import com.example.cookieclickerbutimirritatednow.R.id.adapter_building_icon as building_icon
import com.example.cookieclickerbutimirritatednow.R.id.adapter_building_name as building_name
import com.example.cookieclickerbutimirritatednow.R.id.adapter_building_quantity as building_quantity
import com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses.BuyButton as BuyBut

class BuyListAdapter(
	context: Context,
	private val resource: Int,
	private var objects: List<BuyBut>
) : AA<BuyBut>(context, resource, objects) {
	override fun getView(position: Int, convertView: View?, parent: VG): View {
		val view =
			(requireNonNull(context).getSystemService(LAYOUT_INFLATER_SERVICE) as LI).inflate(
				resource,
				null
			)
		val currentBuyButton = objects[position]
		currentBuyButton.run {
			view.run {
				val buyButton = findViewById<CL>(adapter_building_buy_tile)
				findViewById<IV>(building_icon).setImageResource(imageID)
				findViewById<TV>(building_name).text = name
//						val temp = "${truncate(cost.toDouble())}"
				findViewById<TV>(building_cost).text = "$cost"
				findViewById<TV>(building_quantity).text =
					buildingQuantities[position].toString()
//				while (Main.cookie_quantity.get() >= cost.toDouble()) {
				buyButton.isEnabled = true
				buyButton.setOnClickListener {
					if (Main.cookie_quantity.get() - cost.toDouble() >= 0) {
						this.run {
							Main.cookie_quantity.getAndUpdate { it - cost.toDouble() }
							Main.cps.getAndUpdate { it + cps }
							CookieFragment.cpsQuantity.get().text = Main.cps.get().toString()
						}
						findViewById<TV>(building_quantity).run {
							text = (valueOf(text.toString()) + 1).toString()
							buildingQuantities[position] = valueOf(text.toString()).toLong()
						}
					}
				}
//					if (Main.cookie_quantity.get() < currentBuyButton.cost.toDouble()) buyButton.isClickable =
//						false
//				}
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