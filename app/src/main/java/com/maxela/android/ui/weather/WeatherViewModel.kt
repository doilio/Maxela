package com.maxela.android.ui.weather

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxela.android.data.Repository
import com.maxela.android.model.WeatherResult
import com.maxela.android.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val repository: Repository,
    preference: SharedPreferences
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val language = preference.getString(LANGUAGE_KEY, DEFAULT_LANGUAGE)!!
    private val unit = preference.getString(UNIT_KEY, DEFAULT_UNIT)!!

    // In-memory caching
    private var currentWeatherData: MutableLiveData<List<WeatherResult>>? = null
    private var currentLanguage: String? = null
    private var currentUnit: String? = null

    fun getWeatherResults(): LiveData<List<WeatherResult>> {
        val weatherData = MutableLiveData<List<WeatherResult>>()

        val lastResult = currentWeatherData
        if (language == currentLanguage && unit == currentUnit && lastResult != null) {
            return lastResult
        }

        currentLanguage = language
        currentUnit = unit
        uiScope.launch {
            weatherData.value = when (val result = repository.getWeatherResult(language, unit)) {
                is Result.Success -> result.data.list
                is Result.Error -> null
            }
            currentWeatherData = weatherData
        }
        return weatherData
    }
}