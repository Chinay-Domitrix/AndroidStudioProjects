package com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses

import org.json.JSONObject
import androidx.annotation.DrawableRes as DR
import kotlin.CharSequence as CharSeq

class BuildingStats(
	@param:DR val imageID: Int,
	val name: CharSeq,
	val quantity: Long,
	val cps: Double
) {
	fun toJSONObject() = JSONObject().apply {
		put("Image ID", imageID)
		put("Name", "$name")
		put("Quantity", quantity)
		put("CpS", cps)
	}

	override fun toString() = JSONObject().apply {
		put("Image ID", imageID)
		put("Name", "$name")
		put("Cost", quantity)
		put("CpS", cps)
	}.toString()
}