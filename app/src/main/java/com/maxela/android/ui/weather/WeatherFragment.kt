package com.maxela.android.ui.weather

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxela.android.R
import com.maxela.android.adapter.WeatherAdapter
import com.maxela.android.adapter.WeatherClickListener
import com.maxela.android.databinding.FragmentWeatherBinding
import com.maxela.android.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.weather_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingsActivity -> openSettings()
        }
        return NavigationUI.onNavDestinationSelected(
            item,
            findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun openSettings() {
        startActivity(Intent(activity, SettingsActivity::class.java))
    }

    private fun initComponents() {
        setHasOptionsMenu(true)
        // Init adapter
        adapter = WeatherAdapter(
            WeatherClickListener {
                val action =
                    WeatherFragmentDirections.actionWeatherFragmentToWeatherDetailsFragment(it)
                findNavController().navigate(action)
            }
        )

        // Setup recyclerview
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