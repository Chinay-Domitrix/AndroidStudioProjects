package com.example.randomtesting

import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeStream
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log.e
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.randomtesting.R.id.imageView
import com.example.randomtesting.R.layout.activity_main
import java.net.URL


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(activity_main)
		DownloadImageTask(findViewById(imageView), "https://orteil.dashnet.org/cookieclicker/img/buildings.png").execute()
	}

	private class DownloadImageTask(var bmImage: ImageView, var url: String) : AsyncTask<String?, Void?, Bitmap?>() {
		protected override fun doInBackground(vararg params: String?): Bitmap? {
			var mIcon11: Bitmap? = null
			try {
				mIcon11 = decodeStream(URL(url).openStream())
			} catch (e: Exception) {
				e.message?.let { e("Error", it) }
				e.printStackTrace()
			}
			return mIcon11
		}

		override fun onPostExecute(result: Bitmap?) = bmImage.setImageBitmap(result)
	}
}