package com.maxela.android.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maxela.android.adapter.WeatherAdapter
import com.maxela.android.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        fetchWeatherData()

        binding.buttonReload.setOnClickListener { fetchWeatherData() }
    }

    private fun initComponents() {
        adapter = WeatherAdapter()

        binding.recyclerWeather.hasFixedSize()
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerWeather.layoutManager = layoutManager
        binding.recyclerWeather.adapter = adapter
    }

    private fun fetchWeatherData() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getWeatherResults().observe(viewLifecycleOwner, {
            it?.let {
                if (it.isNotEmpty()) {
                    adapter.submitList(it)
                } else {
                    binding.buttonReload.visibility = View.VISIBLE
                    binding.errorText.visibility = View.VISIBLE
                }
                binding.progressBar.visibility = View.GONE
            }
        })
    }

}