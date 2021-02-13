package com.maxela.android.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse (

	@SerializedName("cnt") val cnt : Int,
	@SerializedName("list") val list : List<WeatherResult>
)