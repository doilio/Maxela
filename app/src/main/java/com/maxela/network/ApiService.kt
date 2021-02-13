package com.maxela.network

import com.maxela.android.model.WeatherResponse
import com.maxela.android.utils.APPID
import com.maxela.android.utils.ID
import com.maxela.android.utils.LANG
import com.maxela.android.utils.UNITS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("group")
    suspend fun getWeatherData(
        @Query(ID) id: String,
        @Query(APPID) apiKey: String,
        @Query(LANG) language: String = "",
        @Query(UNITS) units: String = ""
    ): WeatherResponse
}