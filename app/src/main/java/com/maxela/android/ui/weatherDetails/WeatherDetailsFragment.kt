package com.maxela.android.ui.weatherDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.maxela.android.databinding.FragmentWeatherDetailsBinding
import com.maxela.android.model.WeatherResult


class WeatherDetailsFragment : Fragment() {

    private lateinit var binding: FragmentWeatherDetailsBinding
    private lateinit var argument: WeatherResult

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        argument = WeatherDetailsFragmentArgs.fromBundle(requireArguments()).weatherResult

        setActionBar("${argument.name},${argument.sys.country}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateUI()
    }

    private fun populateUI() {
        if (::argument.isInitialized) {
            binding.weatherResult = argument
            binding.executePendingBindings()
        }
    }

    private fun setActionBar(title: String) {
        ((activity as AppCompatActivity).supportActionBar)?.title = title
        setHasOptionsMenu(true)
    }

}