package com.example.weatherappsample1yt.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappsample1yt.R
import com.example.weatherappsample1yt.adapter.CityAdapter
import com.example.weatherappsample1yt.databinding.ActivityCityBinding
import com.example.weatherappsample1yt.repository.CityRepository
import com.example.weatherappsample1yt.server.ApiClient
import com.example.weatherappsample1yt.server.ApiProvider
import com.example.weatherappsample1yt.server.ApiService_OpenWeather
import com.example.weatherappsample1yt.viewmodel.CityViewModel
import com.example.weatherappsample1yt.viewmodel.CityViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCityBinding
    private val cityAdapter by lazy { CityAdapter() }
    private lateinit var cityViewModel: CityViewModel

    companion object {
        private const val TAG = "CityActivity"
    }

    private fun dpToPx(dp: Int, context: Context): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = CityRepository(ApiClient().getClient(ApiProvider.OPEN_WEATHER).create(ApiService_OpenWeather::class.java))
        val factory = CityViewModelFactory(repository)
        cityViewModel = ViewModelProvider(this, factory)[CityViewModel::class.java]

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }

        binding.apply {
            cityEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: android.text.Editable?) {
                    progressBar2.visibility = View.VISIBLE
                    CoroutineScope(Dispatchers.Main).launch {
                        cityViewModel.loadCity(s.toString(), 10, { cityResponseApi ->
                            // Handle successful response
                            progressBar2.visibility = View.GONE
                            cityAdapter.differ.submitList(cityResponseApi)
                            cityView.apply {
                                layoutManager = LinearLayoutManager(
                                    this@CityActivity, LinearLayoutManager.HORIZONTAL, false
                                )
                                adapter = cityAdapter
                            }
                        }, { throwable ->
                            // Handle error
                            progressBar2.visibility = View.GONE
                            // Log the error
                            Log.e(TAG, "Error loading city data", throwable)
                            // Show a toast with the error message
                            Toast.makeText(this@CityActivity, throwable.message, Toast.LENGTH_LONG)
                                .show()
                            // Or show a dialog with the error message
                            AlertDialog.Builder(this@CityActivity).setTitle("Error")
                                .setMessage(throwable.message).setPositiveButton("OK", null).show()
                        })
                    }
                }
            })
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val additionalPadding = dpToPx(16, v.context)
            v.setPadding(
                systemBars.left + additionalPadding,
                systemBars.top + additionalPadding,
                systemBars.right + additionalPadding,
                systemBars.bottom + additionalPadding
            )
            insets
        }
    }
}