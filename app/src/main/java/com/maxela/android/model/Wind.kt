package com.maxela.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(

	@SerializedName("speed") val speed: Double,
	@SerializedName("deg") val deg: Int
) : Parcelable