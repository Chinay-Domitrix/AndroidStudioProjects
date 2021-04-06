package com.example.cookie_clicker.ui.buildings

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.cookie_clicker.MainActivity
import com.example.cookie_clicker.MainActivity.Companion.cookie_quantity
import com.example.cookie_clicker.R.drawable.*
import com.example.cookie_clicker.R.id.*
import com.example.cookie_clicker.R.layout.fragment_building
import java.lang.Integer.valueOf
import java.math.BigInteger
import java.util.Objects.*
import kotlin.math.truncate


class BuildingFragment : Fragment() {
	private val statsImageIDs = intArrayOf(
		stats_cursor,
		stats_grandma,
		stats_farm,
		stats_mine,
		stats_factory,
		stats_bank,
		stats_temple,
		stats_wizard_tower,
		stats_shipment,
		stats_alchemy_lab,
		stats_portal,
		stats_time_machine,
		stats_antimatter_condenser,
		stats_prism,
		stats_chancemaker,
		stats_fractal_engine,
		stats_javascript_console,
		stats_idleverse
	)
	private var buildingStats = ArrayList<BuildingStats>()

	companion object {
		var buyButtons = arrayListOf(
			BuyButton(
				cursor_icon,
				"Cursor",
				15,
				0.1
			),
			BuyButton(
				grandma_icon,
				"Grandma",
				100,
				1
			),
			BuyButton(
				farm_icon,
				"Farm",
				1_100,
				8
			),
			BuyButton(
				mine_icon,
				"Mine",
				12_000,
				47
			),
			BuyButton(
				factory_icon,
				"Factory",
				130_000,
				260
			),
			BuyButton(
				bank_icon,
				"Bank",
				1_400_000,
				1_400
			),
			BuyButton(
				temple_icon,
				"Temple",
				20_000_000,
				7_800
			),
			BuyButton(
				wizard_tower_icon,
				"Wizard Tower",
				330_000_000,
				44_000
			),
			BuyButton(
				shipment_icon,
				"Shipment",
				5_100_000_000,
				260_000
			),
			BuyButton(
				alchemy_lab_icon,
				"Alchemy Lab",
				75_000_000_000,
				1_600_000
			),
			BuyButton(
				portal_icon,
				"Portal",
				1_000_000_000_000,
				10_000_000
			),
			BuyButton(
				time_machine_icon,
				"Time Machine",
				14_000_000_000_000,
				65_000_000
			),
			BuyButton(
				antimatter_condenser_icon,
				"Antimatter Condenser",
				170_000_000_000_000,
				430_000_000
			),
			BuyButton(
				prism_icon,
				"Prism",
				2_100_000_000_000_000,
				2_900_000_000
			),
			BuyButton(
				chancemaker_icon,
				"Chancemaker",
				26_000_000_000_000_000,
				21_000_000_000
			),
			BuyButton(
				fractal_engine_icon,
				"Fractal Engine",
				310_000_000_000_000_000,
				150_000_000_000
			),
			BuyButton(
				javascript_console_icon,
				"Javascript Console",
				BigInteger("71000000000000000000"),
				1_100_000_000_000.toDouble()
			),
			BuyButton(
				idleverse_icon,
				"Idleverse",
				BigInteger("12000000000000000000000"),
				8_300_000_000_000.toDouble()
			)
		)
		var buildingQuantities = LongArray(18)
	}

	private lateinit var buyListAdapter: BuyListAdapter
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val root = inflater.inflate(fragment_building, container, false)
		for (i in 0 until buyButtons.size) buildingStats.add(
			BuildingStats(
				statsImageIDs[i],
				buyButtons[i].name,
				buildingQuantities[i],
				buyButtons[i].cps
			)
		)
		buyListAdapter = BuyListAdapter(root.context, buy_list, buyButtons)
		return root
	}
}

class BuyButton(val imageID: Int, val name: CharSequence, val cost: BigInteger, val cps: Double) {
	constructor(imageID: Int, name: String, cost: Long, cps: Long) : this(
		imageID,
		name,
		cost.toBigInteger(),
		cps.toDouble()
	)

	constructor(imageID: Int, name: String, cost: Int, cps: Double) : this(
		imageID,
		name,
		cost.toBigInteger(),
		cps
	)

	constructor(imageID: Int, name: String, cost: Int, cps: Int) : this(
		imageID,
		name,
		cost.toBigInteger(),
		cps.toDouble()
	)
}

class BuyListAdapter(
	context: Context,
	private val resource: Int,
	private var objects: List<BuyButton>
) : ArrayAdapter<BuyButton>(context, resource, objects) {
	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val view =
			(requireNonNull(context).getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
				resource,
				null
			)
		val buyButton = view.findViewById<ConstraintLayout>(adapter_building_buy_tile)
		val currentBuyButton = objects[position]
		currentBuyButton.run {
			view.run {
				findViewById<ImageView>(adapter_building_icon).setImageResource(imageID)
				findViewById<TextView>(adapter_building_name).text = name
				val temp = truncate(currentBuyButton.cost.toDouble()).toString()
//				findViewById<TextView>(adapter_building_cost).text =
//					temp.substring(0, temp.indexOf('.'))
				findViewById<TextView>(adapter_building_quantity).text=BuildingFragment.buildingQuantities[position].toString()
			}
		}
		if (cookie_quantity.get() >= currentBuyButton.cost.toDouble()) {
			buyButton.isClickable = true
			val buildingQuantity = view.findViewById<TextView>(adapter_building_quantity)
			buyButton.setOnClickListener {
				if (cookie_quantity.get() - currentBuyButton.cost.toDouble() > 0) {
					currentBuyButton.run {
						cookie_quantity.getAndUpdate { it - cost.toDouble() }
						MainActivity.cps.getAndUpdate { it - cps }
					}
					buildingQuantity.run {
						text = (valueOf(text.toString()) + 1).toString()
						BuildingFragment.buildingQuantities[position] =
							valueOf(text.toString()).toLong()
					}
				}
			}
		}
		return view
	}
}

class BuildingStats(val imageID: Int, val name: CharSequence, val quantity: Long, val cps: Double)

class StatsAdapter(
	context: Context,
	private val resource: Int,
	private val objects: List<BuildingStats>
) : ArrayAdapter<BuildingStats>(context, resource, objects) {
	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val view =
			(requireNonNull(context).getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
				resource,
				null
			)
		objects[position].run {
			view.findViewById<ImageView>(adapter_stats_building_icon).setImageResource(imageID)
			view.findViewById<TextView>(adapter_stats_building_name).text = name
			"CpS per Building: $cps".also {
				view.findViewById<TextView>(
					adapter_stats_unit_cps
				).text = it
			}
			"Current Max Achievable CpS: ${cps * quantity}}".also {
				view.findViewById<TextView>(
					adapter_stats_max_cps
				).text = it
			}
		}
		return view
	}
}