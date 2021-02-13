package com.maxela.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxela.android.databinding.WeatherHeaderItemBinding
import com.maxela.android.model.WeatherResult

class HeaderViewHolder(private val binding: WeatherHeaderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherResult?, clickListener: WeatherClickListener) {
        binding.weatherResult = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): HeaderViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = WeatherHeaderItemBinding.inflate(inflater, parent, false)

            return HeaderViewHolder(binding)
        }
    }
}