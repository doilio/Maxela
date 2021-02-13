package com.maxela.android.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxela.android.model.Weather
import com.maxela.android.model.WeatherResult
import com.maxela.android.utils.HEADER_VIEW_TYPE
import com.maxela.android.utils.WEATHER_VIEW_TYPE
import java.lang.ClassCastException

class WeatherAdapter(private val clickListener: WeatherClickListener) :
    ListAdapter<WeatherResult, RecyclerView.ViewHolder>(WeatherDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_VIEW_TYPE -> HeaderViewHolder.from(parent)
            WEATHER_VIEW_TYPE -> WeatherViewHolder.from(parent)
            else -> throw ClassCastException("Unknown ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind(getItem(position), clickListener)
            }
            is WeatherViewHolder -> {
                holder.bind(getItem(position), clickListener)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).id) {
            1040652 -> HEADER_VIEW_TYPE
            else -> WEATHER_VIEW_TYPE
        }
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