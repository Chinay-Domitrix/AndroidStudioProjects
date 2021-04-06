package com.example.cookieclickerbutimirritatednow.ui.buildings

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.cookieclickerbutimirritatednow.R.drawable.*
import com.example.cookieclickerbutimirritatednow.R.id.buy_list
import com.example.cookieclickerbutimirritatednow.R.id.stats_list
import com.example.cookieclickerbutimirritatednow.R.layout.*
import com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses.BuildingStats
import com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses.BuyButton
import kotlin.concurrent.thread
import android.view.LayoutInflater as LI
import android.view.ViewGroup as VG
import com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses.BuyListAdapter as BLA
import com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses.StatsListAdapter as SLA
import java.math.BigInteger as BigInt


internal class BuildingFragment : Fragment() {
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
		var buildingQuantities = LongArray(18)
		val buyButtons = arrayListOf(
			BuyButton(cursor_icon, "Cursor", 2, 0.1),
			BuyButton(grandma_icon, "Grandma", 100, 1),
			BuyButton(farm_icon, "Farm", 1_100, 8),
			BuyButton(mine_icon, "Mine", 12_000, 47),
			BuyButton(factory_icon, "Factory", 130_000, 260),
			BuyButton(bank_icon, "Bank", 1_400_000, 1_400),
			BuyButton(temple_icon, "Temple", 20_000_000, 7_800),
			BuyButton(wizard_tower_icon, "Wizard Tower", 330_000_000, 44_000),
			BuyButton(shipment_icon, "Shipment", 5_100_000_000, 260_000),
			BuyButton(alchemy_lab_icon, "Alchemy Lab", 75_000_000_000, 1_600_000),
			BuyButton(portal_icon, "Portal", 1_000_000_000_000, 10_000_000),
			BuyButton(time_machine_icon, "Time Machine", 14_000_000_000_000, 65_000_000),
			BuyButton(
				antimatter_condenser_icon,
				"Antimatter Condenser",
				170_000_000_000_000,
				430_000_000
			),
			BuyButton(prism_icon, "Prism", 2_100_000_000_000_000, 2_900_000_000),
			BuyButton(chancemaker_icon, "Chancemaker", 26_000_000_000_000_000, 21_000_000_000),
			BuyButton(
				fractal_engine_icon,
				"Fractal Engine",
				310_000_000_000_000_000,
				150_000_000_000
			),
			BuyButton(
				javascript_console_icon,
				"Javascript Console",
				BigInt("71000000000000000000"),
				1_100_000_000_000.0
			),
			BuyButton(
				idleverse_icon,
				"Idleverse",
				BigInt("12000000000000000000000"),
				8_300_000_000_000.0
			)
		)

	}

	private var buyListAdapter: BLA? = null
	private var statsListAdapter: SLA? = null
	override fun onCreateView(inflater: LI, container: VG?, savedInstanceState: Bundle?): View? {
		val root = inflater.inflate(fragment_building, container, false)
		root.run {
			thread {
				for (i in 0 until buyButtons.size) buildingStats.add(
					BuildingStats(
						statsImageIDs[i],
						buyButtons[i].name,
						buildingQuantities[i],
						buyButtons[i].cps
					)
				)
				buyListAdapter = BLA(context, adapter_buy, buyButtons)
				findViewById<ListView>(buy_list).adapter = buyListAdapter
				/*var tempJSONObject =
					JSONObject().apply {
						put(
							"Buy Buttons",
							JSONArray().apply { buyButtons.forEach { put(it.toJSONObject()) } })
						put("Buy List Adapter", buyListAdapter!!.toJSONObject())
					}
				d("Buy", tempJSONObject.toString())*/
				statsListAdapter = SLA(context, adapter_stats, buildingStats)
				findViewById<ListView>(stats_list).adapter = statsListAdapter
				/*tempJSONObject =
					JSONObject().apply {
						put(
							"Stats",
							JSONArray().apply { buildingStats.forEach { put(it.toJSONObject()) } })
						put("Stats List Adapter", statsListListAdapter!!.toJSONObject())
					}
				d("Buy", tempJSONObject.toString())*/
			}
		}
		return root
//		wtf("BREH", """MY GUY WHY DON'T YOU REACH HERE?!""")
	}
}

