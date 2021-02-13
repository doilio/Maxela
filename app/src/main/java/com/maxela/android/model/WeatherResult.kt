package com.maxela.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResult(

	@SerializedName("coord") val coord: Coord,
	@SerializedName("sys") val sys: Sys,
	@SerializedName("weather") val weather: List<Weather>,
	@SerializedName("main") val main: Main,
	@SerializedName("visibility") val visibility: Int,
	@SerializedName("wind") val wind: Wind,
	@SerializedName("clouds") val clouds: Clouds,
	@SerializedName("dt") val dt: Int,
	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String
) : Parcelable