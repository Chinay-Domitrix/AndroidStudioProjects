package com.example.jsondemo

import java.util.*

object testing {
	@JvmStatic
	fun main(args: Array<String>) {
		val stringList = ArrayList(listOf("Mark Ronson Featuring Bruno Mars", "Uptown Funk", "LMFAO Featuring Lauren Bennett & GoonRock", "Party Rock Anthem", "Ed Sheeran", "Shape Of You", "The Chainsmokers Featuring Halsey", "Closer", "Maroon 5 Featuring Cardi B", "Girls Like You"))
		var i = 0
		while (i < stringList.size) {
			stringList[i]
			stringList[i + 1]
			i += 2
		}
	}
}