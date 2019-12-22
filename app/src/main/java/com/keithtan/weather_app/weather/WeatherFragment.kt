package com.keithtan.weather_app.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.keithtan.weather_app.databinding.FragmentWeatherBinding
import timber.log.Timber

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        Timber.d("test")

        val binding = FragmentWeatherBinding.inflate(inflater)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
