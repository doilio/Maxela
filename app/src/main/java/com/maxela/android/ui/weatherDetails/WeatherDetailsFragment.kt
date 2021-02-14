package com.maxela.android.ui.weatherDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.maxela.android.R
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareWeather -> shareTemperature()
            R.id.openMap -> openLocation()
        }

        return super.onOptionsItemSelected(item)
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

    private fun shareTemperature() {
        val msg = String.format(
            getString(R.string.share_text),
            argument.name,
            argument.sys.country,
            argument.main.temp.toInt(),
            getString(R.string.playstore_link)
        )

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, msg)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_temperature))
        startActivity(shareIntent)
    }

    private fun openLocation() {
        val locationUri = Uri.parse("geo:${argument.coord.lat},${argument.coord.lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)

    }

}