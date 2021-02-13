package com.maxela.android.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.maxela.android.model.WeatherResult
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("dateTime")
fun TextView.setDateTime(dateTime: Int?) {

    dateTime?.let {
        val longDate = dateTime.toLong() * 1000
        val date = Date(longDate)
        val format = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm")
        text = format.format(date)
    }
}

@BindingAdapter("weekday")
fun TextView.setWeekday(dateTime: Int?) {

    dateTime?.let {
        val longDate = dateTime.toLong() * 1000
        val date = Date(longDate)
        val format = SimpleDateFormat("EEEE")
        text = format.format(date)
    }
}

@BindingAdapter("date")
fun TextView.setDate(date: Int?) {

    date?.let {
        val longDate = date.toLong() * 1000
        val data = Date(longDate)
        val format = SimpleDateFormat("dd MMMM yyyy HH:mm")
        text = format.format(data)
    }
}

@BindingAdapter("time")
fun TextView.setTime(time: Int?) {

    time?.let {
        val longDate = time.toLong() * 1000
        val date = Date(longDate)
        val format = SimpleDateFormat("HH:mm")
        text = format.format(date)
    }
}

@BindingAdapter("humidity")
fun TextView.setHumidity(humidity: Int?) {

    humidity?.let {
        text = "$it%"
    }
}
@BindingAdapter("pressure")
fun TextView.setPressure(pressure: Int?) {

    pressure?.let {
        text = String.format("$it hPa")
    }
}

@BindingAdapter("speed")
fun TextView.setWindSpeed(speed: Double?) {

    speed?.let {
        text = String.format("$it km/h")
    }
}