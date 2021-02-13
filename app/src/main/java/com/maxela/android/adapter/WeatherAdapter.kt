package com.maxela.android.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxela.android.model.WeatherResult

class WeatherAdapter(private val clickListener: WeatherClickListener) :
    ListAdapter<WeatherResult, RecyclerView.ViewHolder>(WeatherDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeatherViewHolder).bind(getItem(position), clickListener)
    }
}

class WeatherDiffUtilCallback : DiffUtil.ItemCallback<WeatherResult>() {
    override fun areItemsTheSame(oldItem: WeatherResult, newItem: WeatherResult): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WeatherResult, newItem: WeatherResult): Boolean =
        oldItem == newItem
}

class WeatherClickListener(val clickListener: (weather: WeatherResult) -> Unit) {
    fun onClick(weather: WeatherResult) = clickListener(weather)
}