package com.maxela.android.data

import com.maxela.android.model.WeatherResponse
import com.maxela.android.utils.API_KEY
import com.maxela.android.utils.CITIES
import com.maxela.android.utils.Result
import com.maxela.android.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService
) {

    suspend fun getWeatherResult(language: String, unit: String): Result<WeatherResponse> {
        return withContext(Dispatchers.IO) {

            try {
                val weatherResult = service.getWeatherData(CITIES, API_KEY, language, unit)
                Result.Success(weatherResult)

            } catch (exception: IOException) {
                Result.Error(exception)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }
}