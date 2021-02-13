package com.maxela.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coord (

	@SerializedName("lon") val lon : Double,
	@SerializedName("lat") val lat : Double
):Parcelable