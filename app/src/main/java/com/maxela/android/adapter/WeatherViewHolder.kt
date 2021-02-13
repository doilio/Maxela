package com.maxela.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxela.android.databinding.WeatherListItemBinding
import com.maxela.android.model.WeatherResult

class WeatherViewHolder(private val binding: WeatherListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherResult?, clickListener: WeatherClickListener) {
        binding.weatherResult = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): WeatherViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = WeatherListItemBinding.inflate(inflater, parent, false)

            return WeatherViewHolder(binding)
        }
    }
}