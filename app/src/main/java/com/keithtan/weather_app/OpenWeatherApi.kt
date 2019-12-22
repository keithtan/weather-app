package com.keithtan.weather_app

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

interface OpenWeatherApiService {

    @GET("weather")
    fun dailyForecast(@Query("q") cityName: String) : Call<String>

}

private val client = OkHttpClient.Builder()
    .addInterceptor(OpenWeatherInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object OpenWeatherApi {
    val retrofitService : OpenWeatherApiService by lazy {
        retrofit.create(OpenWeatherApiService::class.java)
    }
}

class OpenWeatherInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("APPID", BuildConfig.OPEN_WEATHER_API_KEY)
            .addQueryParameter("mode", "json")
            .addQueryParameter("units", "metric")
            .build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .url(url)
                .build()
        )
    }

}