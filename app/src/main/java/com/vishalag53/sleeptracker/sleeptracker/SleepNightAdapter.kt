package com.vishalag53.sleeptracker.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vishalag53.sleeptracker.TextItemViewHolder
import com.vishalag53.sleeptracker.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>(){

    var data = listOf<SleepNight>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }
}