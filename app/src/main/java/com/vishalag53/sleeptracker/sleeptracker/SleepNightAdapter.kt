package com.vishalag53.sleeptracker.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.sleeptracker.R
import com.vishalag53.sleeptracker.TextItemViewHolder
import com.vishalag53.sleeptracker.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>(){

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view,parent,false) as TextView
        return TextItemViewHolder(view)
    }



}