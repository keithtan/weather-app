package com.keithtan.weather_app.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keithtan.weather_app.OpenWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeatherViewModel : ViewModel() {

    private val _weather = MutableLiveData<String>()
    init {
        _weather.value = "hello"
    }
    val weather: LiveData<String>
        get() = _weather

    init {
        getWeather()
    }

    private fun getWeather() {
        OpenWeatherApi
            .retrofitService
            .dailyForecast("London")
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Timber.d("test1")
                    _weather.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Timber.d("test2")
                    Timber.d("test ${response.body()}")
                    _weather.value = response.body()
                }
            })
    }


}
