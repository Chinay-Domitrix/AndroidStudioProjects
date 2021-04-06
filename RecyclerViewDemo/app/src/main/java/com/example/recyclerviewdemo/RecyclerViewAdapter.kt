package com.example.recyclerviewdemo

import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerviewdemo.R.id.button_list
import com.example.recyclerviewdemo.R.id.textView_list
import com.example.recyclerviewdemo.R.layout.holder_view
import com.example.recyclerviewdemo.RecyclerViewAdapter.RecyclerViewHolder
import java.util.*

class RecyclerViewAdapter(var parentContext: Context, var list: ArrayList<String>) :
    Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(from(parentContext).inflate(holder_view, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val tempArray = arrayOf("TextView $position", "Button $position")
        holder.textView.text = tempArray[0]
        holder.button.text = tempArray[1]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RecyclerViewHolder(itemView: View?) : ViewHolder(itemView!!) {
        lateinit var button: Button
        lateinit var textView: TextView

        init {
            d("TAG", "Creating Holder")
            if (itemView != null) {
                button = itemView.findViewById(button_list)
                textView = itemView.findViewById(textView_list)
            }
        }
    }
}