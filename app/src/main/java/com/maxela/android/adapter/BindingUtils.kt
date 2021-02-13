package com.maxela.android.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.maxela.android.model.WeatherResult

@BindingAdapter("weatherIcon")
fun ImageView.setWeatherIcon(imageUrl: String?) {

    imageUrl?.let {
        Glide.with(this).load(it).into(this)
    }
}

@BindingAdapter("cityAndCountry")
fun TextView.setCityAndCountry(weatherResult: WeatherResult?) {

    weatherResult?.let {
        text = String.format("${it.name},${it.sys.country}")
    }
}

@BindingAdapter("temperature")
fun TextView.setTemperature(temperature: Double?) {

    temperature?.let {
       text = String.format("${temperature.toInt()}°")
    }

}