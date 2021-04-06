package com.example.recyclerviewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.R.id.recycler_view
import com.example.recyclerviewdemo.R.layout.activity_main

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        recyclerView = findViewById(recycler_view)
        val list = ArrayList<String>()
        for (i in 0..10) list.add(i.toString())
        recyclerView.adapter = RecyclerViewAdapter(this, list)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}