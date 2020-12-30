package com.example.layoutsandviewsproject

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.layoutsandviewsproject.ListAdapter.DescriptionAccessor
import com.example.layoutsandviewsproject.R.drawable.*
import com.example.layoutsandviewsproject.R.id.*
import com.example.layoutsandviewsproject.R.layout.activity_main
import com.example.layoutsandviewsproject.R.layout.adapter_list
import com.example.layoutsandviewsproject.Values.Companion.orientation
import java.util.*
import java.util.Objects.*

open class MainActivity : AppCompatActivity(), DescriptionAccessor {
	private var mangoes = ArrayList<Mango>()
	private lateinit var listAdapter: ListAdapter
	private lateinit var booleans: BooleanArray
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		mangoes.addAll(listOf(
				Mango(
						honey,
						"Honey",
						"""
							- Aroma: Tropical fruit and peachy notes
							- Texture: Smooth, firm flesh with no fibers
							- Color: Vibrant yellow
							- Shape: Small, flattened oblong shape
							- Ripening Cues: Skin turns to a deep golden color and small wrinkles appear when fully ripe.
							- Primary Source Country: Mexico, Peru, Ecuador, Brazil
							- Peak Availability: March to July
							""".trimIndent()
				),
				Mango(
						francis,
						"Francis",
						"""
							- Aroma: Peach and tropical fruit
							- Texture: Soft, juicy flesh with fibers
							- Color: Bright yellow skin with green overtones
							- Shape: Oblong and sigmoid S-shape
							- Ripening Cues: Green overtones diminish and the yellow becomes more golden as the mango ripens.
							- Primary Source Country: Haiti, Ecuador
							- Peak Availability: May to June
							""".trimIndent()
				),
				Mango(
						haden,
						"Haden",
						"""
							- Aroma: Tropical fruit with high peach notes
							- Texture: Firm flesh due to fine fibers
							- Color: Bright red with green and yellow overtones and small white dots
							- Shape: Medium to large with an oval to round shape
							- Ripening Cues: Green areas of the mango turn to yellow as it ripens.
							- Primary Source Country: Mexico, Ecuador, Peru
							- Peak Availability: March to May
							""".trimIndent()
				),
				Mango(
						keitt,
						"Keitt",
						"""
							- Aroma: High in citrus notes
							- Texture: Firm, juicy flesh with limited fibers
							- Color: Dark to medium green, sometimes with a pink blush over a small portion of the mango
							- Shape: Large oval shape
							- Ripening Cues: Skin stays green even when ripe.
							- Primary Source Country: Mexico, Ecuador, Brazil, United States
							- Peak Availability: March to April, August and September
							""".trimIndent()
				),
				Mango(
						kent,
						"Kent",
						"""
							- Aroma: Peach and tropical fruit
							- Texture: Juicy, tender flesh with limited fibers
							- Color: Dark green and often has a dark red blush over a small portion of the mango
							- Shape: Large oval shape
							- Ripening Cues: Yellow undertones or dots cover more of the mango as it ripens.
							- Primary Source Country: Mexico, Peru, Ecuador
							- Peak Availability: January, February and December
							""".trimIndent()
				),
				Mango(
						tommy_atkins,
						"Tommy Atkins",
						"""
							- Aroma: Small hints of tropical fruit and citrus
							- Texture: Firm flesh and high fibers throughout
							- Color: A dark red blush often covers much of the fruit with green and orange-yellow accents
							- Shape: Medium to large with oval or oblong shape.
							- Ripening Cues: This mango may not provide any visual cues.
							- Primary Source Country: Mexico, Brazil, Ecuador, Guatemala
							- Peak Availability: March to July
							""".trimIndent()
				)
		))
		val orientation = this.resources.configuration.orientation
		check(orientation == ORIENTATION_PORTRAIT || orientation == ORIENTATION_LANDSCAPE) { "Orientation unavailable or undefined" }
		if (orientation == ORIENTATION_PORTRAIT) Values(1) else {
			Values(2)
		}

		listAdapter = if (savedInstanceState == null) {
			booleans = BooleanArray(mangoes.size)
			for (i in mangoes.indices) booleans[i] = false
			ListAdapter(this, adapter_list, mangoes, booleans)
		} else {
			val imageIDs = savedInstanceState.getIntArray("IDs")
			val names = savedInstanceState.getStringArray("Names")
			val descriptions = savedInstanceState.getStringArray("Descriptions")
			booleans = savedInstanceState.getBooleanArray("View Click State")!!
			val length = imageIDs!!.size
			val mangoArrayList = (0 until length).map { Mango(imageIDs[it], names!![it], descriptions!![it]) }
			ListAdapter(this, adapter_list, mangoArrayList, booleans)
		}
		((if (orientation == ORIENTATION_PORTRAIT) findViewById(layout_listView) else findViewById<View>(land_listView)) as ListView).adapter = listAdapter
	}

	public override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		val listAdapterSize = listAdapter.count
		val imageIDs = IntArray(listAdapterSize)
		val names = arrayOfNulls<String>(listAdapterSize)
		val descriptions = arrayOfNulls<String>(listAdapterSize)
		val booleans = BooleanArray(listAdapterSize)
		for (i in 0 until listAdapterSize) {
			val mango = listAdapter.getItem(i)
			imageIDs[i] = mango!!.imageID
			names[i] = mango.name
			descriptions[i] = mango.description
			booleans[i] = this.booleans[i]
		}
		outState.putIntArray("IDs", imageIDs)
		outState.putStringArray("Names", names)
		outState.putStringArray("Descriptions", descriptions)
		outState.putBooleanArray("View Click State", booleans)
	}

	override fun changeDescriptionText(x: String?) {
		findViewById<TextView>(land_textView).text = x
	}

	override fun setDescriptionVisibility(visibilityInt: Int) {
		findViewById<TextView>(land_textView).visibility = visibilityInt
	}
}

class Mango(val imageID: Int, val name: String, val description: String)

class ListAdapter(context: Context, private val resource: Int, private var objects: List<Mango>, private val booleanArray: BooleanArray) : ArrayAdapter<Mango?>(context, resource, objects) {
	interface DescriptionAccessor {
		fun changeDescriptionText(x: String?)
		fun setDescriptionVisibility(visibilityInt: Int)
	}

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val view = (requireNonNull(context).getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(resource, null)
		val buttonRemove = view.findViewById<Button>(adapter_button_remove)
		val textView = view.findViewById<TextView>(adapter_textView)
		val imageView = view.findViewById<ImageView>(adapter_imageView)
		imageView.setImageResource(objects[position].imageID)
		imageView.contentDescription = objects[position].name
		val s = textView.text.toString() + " " + objects[position].name
		textView.text = s
		val mainActivity = (context as MainActivity)
		if (booleanArray[position]) {
			buttonRemove.visibility = VISIBLE
			booleanArray[position] = true
		}
		view.setOnClickListener {
			if (!booleanArray[position]) {
				buttonRemove.visibility = VISIBLE
				booleanArray[position] = true
			} else {
				buttonRemove.visibility = GONE
				booleanArray[position] = false
			}
			if (orientation == 2) mainActivity.changeDescriptionText(objects[position].description)
		}
		buttonRemove.setOnClickListener {
			val mutableList = objects as MutableList<Mango>
			mutableList.removeAt(position)
			objects = mutableList
			notifyDataSetChanged()
		}
		return view
	}
}

class Values(orientation: Int) {
	companion object {
		var orientation = 0
	}

	init {
		Companion.orientation = orientation
	}
}