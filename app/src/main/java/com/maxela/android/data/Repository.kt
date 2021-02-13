package com.maxela.android.data

import com.maxela.android.model.WeatherResponse
import com.maxela.android.utils.API_KEY
import com.maxela.android.utils.Result
import com.maxela.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: ApiService
) {

    private val listOfCityIds =
        "1040652,2950159,2618425,2964574,2267057,2643743,3117735,2988507,3067696,3169070,2761369"

    suspend fun getWeatherResult(): Result<WeatherResponse> {
        return withContext(Dispatchers.IO) {

            try {
                val weatherResult = service.getWeatherData(listOfCityIds, API_KEY)
                Result.Success(weatherResult)

            } catch (exception: IOException) {
                Result.Error(exception)
            } catch (exception: HttpException) {
                Result.Error(exception)
            }
        }
    }
}