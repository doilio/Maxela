package com.maxela.android.ui.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxela.android.data.Repository
import com.maxela.android.model.WeatherResult
import com.maxela.android.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getWeatherResults(): LiveData<List<WeatherResult>> {
        val weatherData = MutableLiveData<List<WeatherResult>>()

        uiScope.launch {
            weatherData.value = when (val result = repository.getWeatherResult()) {
                is Result.Success -> result.data.list
                is Result.Error -> null
            }
        }
        return weatherData
    }
}