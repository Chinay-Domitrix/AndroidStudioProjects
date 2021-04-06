package com.example.cookieclickerbutimirritatednow.ui.buildings.buildingFragmentsSupplimentaryClasses

import org.json.JSONObject
import androidx.annotation.DrawableRes as DR
import java.math.BigInteger as BigInt
import kotlin.CharSequence as CharSeq

class BuyButton(
	@param:DR val imageID: Int,
	val name: CharSeq,
	val cost: BigInt,
	val cps: Double
) {
	constructor(@DR imageID: Int, name: String, cost: Long, cps: Long) : this(
		imageID,
		name,
		cost.toBigInteger(),
		cps.toDouble()
	)

	constructor(@DR imageID: Int, name: String, cost: Int, cps: Double) : this(
		imageID,
		name,
		cost.toBigInteger(),
		cps
	)

	constructor(@DR imageID: Int, name: String, cost: Int, cps: Int) : this(
		imageID,
		name,
		cost.toBigInteger(),
		cps.toDouble()
	)

	fun toJSONObject() = JSONObject().apply {
		put("Image ID", imageID)
		put("Name", "$name")
		put("Cost", "$cost")
		put("CpS", cps)
	}
	override fun toString() = JSONObject().apply {
		put("Image ID", imageID)
		put("Name", "$name")
		put("Cost", "$cost")
		put("CpS", cps)
	}.toString()
}