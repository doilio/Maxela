package com.maxela.android.model

import com.google.gson.annotations.SerializedName

data class Sys (

	@SerializedName("country") val country : String,
	@SerializedName("timezone") val timezone : Int,
	@SerializedName("sunrise") val sunrise : Int,
	@SerializedName("sunset") val sunset : Int
)