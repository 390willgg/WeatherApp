package com.example.weatherappsample1yt.presentation.view.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappsample1yt.databinding.ActivityMainBinding
import com.example.weatherappsample1yt.domain.useCase.weather.WeatherUseCase
import com.example.weatherappsample1yt.presentation.di.Injector
import javax.inject.Inject

//package com.example.weatherappsample1yt.presentation.view.main
//
//import android.content.Context
//import android.content.Intent
//import android.location.LocationManager
//import android.os.Bundle
//import android.util.Log
//import android.view.WindowManager
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import com.example.weatherappsample1yt.databinding.ActivityMainBinding
//import com.example.weatherappsample1yt.presentation.view.city.CityActivity
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//
//class MainActivity : AppCompatActivity() {
//    private val RequestCode = 1000
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var mFusedLocationClient: FusedLocationProviderClient
//    private lateinit var locationCallback: LocationCallback
//    private val forecastAdapter by lazy {
//        ForeCastAdapter()
//    }
//
//    private val forecastItemDayAdapter by lazy {
//        ForecastItemDayAdapter()
//    }
//
//    inner class LocationServiceBroadcastReceiver : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            try {
//                if (intent?.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
//                    Log.d(TAG, "Location Providers changed")
//                    if (context != null && isLocationEnabled(context)) {
//                        Toast.makeText(context, "Location Services Enabled", Toast.LENGTH_SHORT)
//                            .show()
//                        if (context is MainActivity) {
//                            context.getLocation()
//                            Log.i(TAG, "Location Services Enabled")
//                        }
//                    } else {
//                        Toast.makeText(context, "Location Services Disabled", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Error in onReceive: ${e.message}")
//            }
//        }
//
//        private fun isLocationEnabled(context: Context): Boolean {
//            var isEnabled = false
//            try {
//                val locationManager: LocationManager =
//                    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//                isEnabled =
//                    locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//                        LocationManager.NETWORK_PROVIDER
//                    )
//            } catch (e: Exception) {
//                Log.e(TAG, "Error checking if location is enabled: ${e.message}")
//            }
//            return isEnabled
//        }
//    }
//
//    private val locationSettingResultLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            try {
//                if (result.resultCode == Activity.RESULT_OK) {
//                    // Cek apakah layanan lokasi sudah diaktifkan
//                    if (isLocationEnabled()) {
//                        // Jika sudah, panggil fungsi untuk mendapatkan lokasi
//                        getLocation()
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Error in locationSettingResultLauncher: ${e.message}")
//            }
//        }
//
//    private lateinit var locationServiceReceiver: LocationServiceBroadcastReceiver
//    private val calender by lazy { Calendar.getInstance() }
//
//    private val repository = getAMSRepository()
//    private val weatherRemoteDataSource = WeatherRemoteDataSourceImpl(repository)
//    private val weatherViewModel = WeatherViewModel(weatherRemoteDataSource)
//
//    override fun onStart() {
//        super.onStart()
//        try {
//            locationServiceReceiver = LocationServiceBroadcastReceiver()
//            registerReceiver(
//                locationServiceReceiver, IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
//            )
//        } catch (e: Exception) {
//            Log.e(TAG, "Error in onStart: ${e.message}")
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        try {
//            enableEdgeToEdge()
//            binding = ActivityMainBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//
//            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//                insets
//            }
//
//            window.apply {
//                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//                statusBarColor = Color.TRANSPARENT
//            }
//
//            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//            locationCallback = object : LocationCallback() {
//                override fun onLocationResult(p0: LocationResult) {
//                    for (location in p0.locations) {
//                        updateWeatherData(location)
//                    }
//                }
//            }
//            getLocation()
//        } catch (e: Exception) {
//            Log.e(TAG, "Error in onCreate: ${e.message}")
//        }
//    }
//
//    private fun isNightNow(): Boolean {
//        return calender.get(Calendar.HOUR_OF_DAY) >= 18
//    }
//
//    private fun setDynamicallyWallpaper(icon: String): Int {
//        return when (icon.dropLast(1)) {
//            "01" -> {
//                initWeatherView(PrecipType.CLEAR)
//                R.drawable.snow_bg
//            }
//
//            "02", "03", "04" -> {
//                initWeatherView(PrecipType.CLEAR)
//                R.drawable.cloudy_bg
//            }
//
//            "09", "10", "11" -> {
//                initWeatherView(PrecipType.RAIN)
//                R.drawable.rainy_bg
//            }
//
//            "13" -> {
//                initWeatherView(PrecipType.SNOW)
//                R.drawable.snow_bg
//            }
//
//            "50" -> {
//                initWeatherView(PrecipType.CLEAR)
//                R.drawable.haze_bg
//            }
//
//            else -> 0
//        }
//    }
//
//    private fun setEffectRainSnow(icon: String) {
//        when (icon.dropLast(1)) {
//            "01" -> {
//                initWeatherView(PrecipType.CLEAR)
//            }
//
//            "02", "03", "04" -> {
//                initWeatherView(PrecipType.CLEAR)
//            }
//
//            "09", "10", "11" -> {
//                initWeatherView(PrecipType.RAIN)
//            }
//
//            "13" -> {
//                initWeatherView(PrecipType.SNOW)
//            }
//
//            "50" -> {
//                initWeatherView(PrecipType.CLEAR)
//            }
//        }
//    }
//
//    private fun initWeatherView(type: PrecipType) {
//        binding.weatherView.apply {
//            setWeatherData(type)
//            angle = -20
//            emissionRate = 100.0f
//        }
//    }
//
//    private fun isLocationEnabled(): Boolean {
//        var isEnabled = false
//        try {
//            val locationManager: LocationManager =
//                getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            isEnabled =
//                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//                    LocationManager.NETWORK_PROVIDER
//                )
//        } catch (e: Exception) {
//            Log.e(TAG, "Error checking if location is enabled: ${e.message}")
//        }
//        return isEnabled
//    }
//
//    private fun checkPermissions(): Boolean {
//        return try {
//            val coarseLocationPermission = ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//
//            val fineLocationPermission = ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//
//            if (!coarseLocationPermission || !fineLocationPermission) {
//                // Provide feedback to the user about the missing permissions
//                // and how they can grant them.
//                return false
//            }
//
//            true
//        } catch (e: SecurityException) {
//            // Only catch the specific Exception that might be thrown by the checkSelfPermission method
//            false
//        }
//    }
//
//    private fun requestPermissions() {
//        ActivityCompat.requestPermissions(
//            this, arrayOf(
//                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
//            ), RequestCode
//        )
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            RequestCode -> {
//                val permissionStatuses = permissions.zip(grantResults.toList()).toMap()
//                permissionStatuses.forEach { (permission, grantResult) ->
//                    when (permission) {
//                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION -> {
//                            if (grantResult == PackageManager.PERMISSION_GRANTED) {
//                                try {
//                                    getLocation()
//                                } catch (e: Exception) {
//                                    Log.e(TAG, "Error getting location", e)
//                                    Toast.makeText(
//                                        this@MainActivity,
//                                        "Error getting location: ${e.message}",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            } else {
//                                Toast.makeText(
//                                    this@MainActivity,
//                                    "Permission denied for access to location",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private fun getLocation() {
//        try {
//            Log.i(TAG, "Attempting to get location")
//            if (!checkPermissions()) {
//                Log.i(TAG, "Permissions not granted, requesting permissions")
//                requestPermissions()
//                return
//            }
//
//            if (!isLocationEnabled()) {
//                Log.i(
//                    TAG, "Location services not enabled, prompting user to enable location services"
//                )
//                promptEnableLocationServices()
//                return
//            }
//
//            if (ActivityCompat.checkSelfPermission(
//                    this, Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                Log.i(TAG, "Permissions not granted, requesting permissions")
//                requestPermissions()
//                return
//            }
//
//            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
//                val location: Location? = task.result
//                if (location != null) {
//                    Log.i(TAG, "Location obtained: $location")
//                    updateWeatherData(location)
//                } else {
//                    Log.i(TAG, "Location is not available, requesting location updates")
//                    requestLocationUpdates()
//                }
//            }
//        } catch (e: Exception) {
//            Log.e(TAG, "Error getting location", e)
//        }
//    }
//
//    private fun requestLocationUpdates() {
//        try {
//            Log.i(TAG, "Requesting location updates")
//            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
//                .setWaitForAccurateLocation(false).setMinUpdateIntervalMillis(500)
//                .setMaxUpdateDelayMillis(1000).build()
//
//            if (ActivityCompat.checkSelfPermission(
//                    this, Manifest.permission.ACCESS_FINE_LOCATION
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                mFusedLocationClient.requestLocationUpdates(
//                    locationRequest, locationCallback, null
//                )
//            } else {
//                Log.i(TAG, "Permissions not granted, requesting permissions")
//                requestPermissions()
//            }
//            binding.progressBar.visibility = View.GONE
//        } catch (e: Exception) {
//            Log.e(TAG, "Error requesting location updates", e)
//        }
//    }
//
//    private fun promptEnableLocationServices() {
//        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//        if (intent.resolveActivity(packageManager) != null) {
//            Toast.makeText(this, R.string.turn_on_location, Toast.LENGTH_LONG).show()
//            locationSettingResultLauncher.launch(intent)
//        } else {
//            Toast.makeText(this, R.string.location_not_supported, Toast.LENGTH_LONG).show()
//        }
//    }
//
//    @Suppress("DEPRECATION")
//    private fun updateWeatherData(location: Location) {
//        val geocoder = Geocoder(this, Locale.getDefault())
//        val list: MutableList<Address>?
//
//        Log.i(TAG, "Updating weather data: $geocoder")
//        try {
//            if (Geocoder.isPresent()) {
//                val latPrimaryData = location.latitude
//                val lonPrimaryData = location.longitude
//                loadWeatherData(latPrimaryData, lonPrimaryData, "metric")
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    geocoder.getFromLocation(latPrimaryData,
//                        lonPrimaryData,
//                        1,
//                        object : GeocodeListener {
//                            override fun onGeocode(addresses: MutableList<Address>) {
//                                if (addresses.isNotEmpty()) {
//                                    val address = addresses[0]
//                                    handleGeoCodeResult(address)
//                                } else {
//                                    Log.e(
//                                        TAG, "No address found for the given latitude and longitude"
//                                    )
//                                }
//                            }
//
//                            override fun onError(errorMessage: String?) {
//                                super.onError(errorMessage)
//                                Log.e(TAG, errorMessage.toString())
//                            }
//                        })
//                } else {
//                    list = geocoder.getFromLocation(location.latitude, location.longitude, 1)
//                    list?.get(0)?.let {
//                        handleGeoCodeResult(it)
//                    }
//                }
//            }
//        } catch (e: Exception) {
//            Log.e(TAG, e.message.toString())
//        }
//    }
//
//    // Helper function for setting spannable text
//    private fun setSpannableText(text: String, symbol: String): SpannableString {
//        val spannable = SpannableString(text)
//        val symbolPosition = text.indexOf(symbol)
//
//        if (symbolPosition != -1) {
//            spannable.setSpan(
//                SuperscriptSpan(),
//                symbolPosition,
//                symbolPosition + symbol.length,
//                Spannable.SPAN_INCLUSIVE_INCLUSIVE
//            )
//            spannable.setSpan(
//                RelativeSizeSpan(0.45f),
//                symbolPosition,
//                symbolPosition + symbol.length,
//                Spannable.SPAN_INCLUSIVE_INCLUSIVE
//            )
//        }
//        return spannable
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mFusedLocationClient.removeLocationUpdates(locationCallback)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        unregisterReceiver(locationServiceReceiver)
//    }
//
//    companion object {
//        const val TAG = "MY MainActivity"
//    }
//
//    private fun handleGeoCodeResult(address: Address) {
//        binding.apply {
//            var lat = intent.getDoubleExtra("lat", 0.0)
//            var lon = intent.getDoubleExtra("lon", 0.0)
//            var name = intent.getStringExtra("name")
//
//            if (lat == 0.0) {
//                lat = address.latitude
//                lon = address.longitude
//                name = address.locality
//            }
//
//            addCity.setOnClickListener {
//                startActivity(Intent(this@MainActivity, CityActivity::class.java))
//            }
//
//            loadWeatherData(lat, lon, "metric", name)
//            loadForecastWeather(lat, lon, "metric")
//        }
//    }
//
//    private fun loadForecastWeather(lat: Double, lon: Double, unit: String) {
//        try {
//            weatherViewModel.forecastWeather.observe(this@MainActivity) { it: ForecastWeatherData? ->
//                if (it != null) {
//                    updateForecastWeatherUI(it)
//                    Log.d(TAG, "Forecast weather data: $it")
//                } else {
//                    Log.e(TAG, "Error: Failed to load forecast weather data")
//                }
//            }
//
//            weatherViewModel.getForecastWeather(lat, lon, unit)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception in loadForecastWeather: ${e.message}")
//        }
//    }
//
//    private fun loadWeatherData(lat: Double, lon: Double, unit: String, name: String? = null) {
//        try {
//            weatherViewModel.currentWeather.observe(this@MainActivity) { it: CurrentWeatherData? ->
//                if (it != null) {
//                    updateCurrentWeatherUI(it, name = name)
//                    Log.d(TAG, "Current weather data: $it")
//                } else {
//                    Log.e(TAG, "Error: Failed to load current weather data")
//                }
//            }
//
//            weatherViewModel.getCurrentWeather(lat, lon, unit)
//        } catch (e: Exception) {
//            Log.e(TAG, "Exception in loadWeatherData: ${e.message}")
//        }
//    }
//
//
//    private fun updateForecastWeatherUI(weather: ForecastWeatherData?) {
//        Log.i(TAG, "Forecast weather data: $weather")
//        weather?.let { forecastWeather ->
//            binding.blueView.visibility = View.VISIBLE
//            forecastAdapter.differ.submitList(forecastWeather.forecasts?.hourlyDetails)
//
//            Log.i(TAG, "Forecast weather data: $forecastWeather")
//            val filteredData = forecastWeather.forecasts?.hourlyDetails?.filter {
//                val forecastDate = it.time?.let { it1 -> parseDate(it1) }
//                val currentDate = Date()
//                val calendar = Calendar.getInstance().apply {
//                    time = currentDate
//                    add(Calendar.DAY_OF_YEAR, 3)
//                }
//                forecastDate?.before(calendar.time) == true
//            }
//
//            val jsonFormat = Gson().toJson(filteredData).replace(",", ",\n")
//            Log.i(TAG, "Forecast weather data in filtered data:\n$jsonFormat")
//
//            val dailyData = filteredData?.groupBy {
//                val forecastDate = it.time?.let { it1 -> parseDate(it1) }
//                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(forecastDate)
//            }?.map { (date, forecasts) ->
//                val maxTemp = forecasts.maxByOrNull { it.maxTemp ?: Double.MIN_VALUE }?.maxTemp
//                val minTemp = forecasts.minByOrNull { it.minTemp ?: Double.MAX_VALUE }?.minTemp
//                val avgTemp = forecasts.map { it.temp ?: 0.0 }.average()
//                val weatherFrequency = forecasts.groupingBy { it.icon }.eachCount()
//                val dominantWeatherIcon = weatherFrequency.maxByOrNull { it.value }?.key
//                val dominantWeatherPercentage = (weatherFrequency[dominantWeatherIcon]?.toDouble()
//                    ?: 0.0) / forecasts.size * 100
//
//                Log.i(
//                    "Forecast",
//                    "Date: $date, Max: $maxTemp, Min: $minTemp, Avg: $avgTemp, Icon: $dominantWeatherIcon, Icon Percentage: $dominantWeatherPercentage%"
//                )
//                com.example.weatherappsample1yt.data.model.ForecastDailyData(
//                    date, maxTemp, minTemp, dominantWeatherIcon
//                )
//            }?.take(3)
//
//            forecastItemDayAdapter.differ.submitList(dailyData)
//
//            binding.forecastView.apply {
//                layoutManager = LinearLayoutManager(
//                    this@MainActivity, LinearLayoutManager.HORIZONTAL, false
//                )
//                adapter = forecastAdapter
//            }
//            binding.forecastView2.apply {
//                layoutManager = LinearLayoutManager(
//                    this@MainActivity, LinearLayoutManager.VERTICAL, false
//                )
//                adapter = forecastItemDayAdapter
//            }
//        } ?: run {
//            // Handle the case when weather is null
//            Log.e(TAG, "Failed to load forecast weather data")
//            Toast.makeText(
//                this@MainActivity, "Failed to load forecast weather data", Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//
//    private fun parseDate(dateString: String): Date? {
//        val dateFormats = listOf(
//            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()),
//            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//        )
//
//        for (format in dateFormats) {
//            try {
//                return format.parse(dateString)
//            } catch (e: ParseException) {
//                // Ignore and try the next format
//                Log.e(TAG, "Error parsing date string", e)
//                continue
//            }
//        }
//        // All attempts to parse the date string failed
//        return null
//    }
//
//    private fun updateCurrentWeatherUI(weather: CurrentWeatherData?, name: String? = null) {
//        weather?.let { currentWeather ->
//            binding.apply {
//                val icon = when (currentWeather.icon.toString()) {
//                    "01d", "0n" -> "sunny"
//                    "02d", "02n" -> "cloudy_sunny"
//                    "03d", "03n" -> "cloudy_sunny"
//                    "04d", "04n" -> "cloudy"
//                    "09d", "09n" -> "rainy"
//                    "10d", "10n" -> "rainy"
//                    "11d", "11n" -> "storm"
//                    "13d", "13n" -> "snow"
//                    "50d", "50n" -> "mist"
//                    else -> "sunny"
//                }
//
//                // Create a map of icon names to drawable resource IDs
//                val iconMap: Map<String, Int> = mapOf(
//                    "sunny" to R.drawable.sunny_day_icon,
//                    "cloudy_sunny" to R.drawable.clear_sky_icon,
//                    "cloudy" to R.drawable.cloudy_icon_logo,
//                    "rainy" to R.drawable.rain_icon_logo,
//                    "storm" to R.drawable.thunder_icon_logo,
//                    "snow" to R.drawable.snow_icon_logo,
//                    "mist" to R.drawable.haze
//                )
//
//                // Get the drawable resource ID from the map
//                val drawableResourceId = iconMap[icon]
//
//                Glide.with(binding.root.context).load(R.drawable.haze_icon)
//                    .into(binding.weatherIcon)
//
//                progressBar.visibility = View.GONE
//                detailLayout.visibility = View.VISIBLE
//
//                getString(
//                    R.string.raining_more_detail_text, currentWeather.weatherDescription
//                ).also {
//                    detailedStatusText.text = it
//                }
//
//                try {
//                    Log.i(TAG, "Rain precipitation: ${currentWeather.precipitation}")
//                    rainPrecipitation.text = getString(
//                        R.string.rain_precipitation_value,
//                        Math.round(currentWeather.precipitation ?: 0.0)
//                    )
//                } catch (e: Exception) {
//                    Log.e(TAG, "Error getting rain precipitation data", e)
//                }
//
//                cityText.text = if (!name.isNullOrEmpty()) name else currentWeather.city
//                statusText.text = currentWeather.weatherStatus
//
//                val windSpeedData = Math.round(currentWeather.windSpeed ?: 0.0)
//                val windTextData = getString(R.string.wind_speed, windSpeedData)
//                windText.text = setSpannableText(windTextData, "m/s")
//
//                val currentTemp = Math.round(currentWeather.temperature ?: 0.0)
//                val currentTempText = getString(R.string.currentTemperaturText, currentTemp)
//                currentTempTv.text = setSpannableText(currentTempText, "°C")
//
//                val maxTemp = Math.round(currentWeather.maxTemperature ?: 0.0)
//                val maxTempTextData = getString(R.string.maxTempText, maxTemp)
//                maxTempText.text = setSpannableText(maxTempTextData, "°C")
//
//                val minTemp = Math.round(currentWeather.minTemperature ?: 0.0)
//                val minTempTextData = getString(R.string.minTemperaturText, minTemp)
//                minTempText.text = setSpannableText(minTempTextData, "°C")
//
//                val humidity = Math.round(currentWeather.humidity?.toDouble() ?: 0.0)
//                val humidityTextData = getString(R.string.humidityDegree, humidity)
//                humidityText.text = setSpannableText(humidityTextData, "%")
//
//                val drawable = if (isNightNow()) {
//                    R.drawable.night_bg
//                } else {
//                    setDynamicallyWallpaper(currentWeather.icon ?: "")
//                }
//
//                setEffectRainSnow(currentWeather.icon ?: "")
//            }
//        } ?: run {
//            // Handle the case when weather is null
//            Log.e(TAG, "Failed to load current weather data")
//            Toast.makeText(
//                this@MainActivity, "Failed to load current weather data", Toast.LENGTH_SHORT
//            ).show()
//        }
//    }
//}

import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    @Named("OWWeather")
    lateinit var owWeatherUseCase: WeatherUseCase

    @Inject
    @Named("WAWeather")
    lateinit var waWeatherUseCase: WeatherUseCase

    @Inject
    @Named("AMSWeather")
    lateinit var amsWeatherUseCase: WeatherUseCase

    private lateinit var selectedWeatherUseCase: WeatherUseCase
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as Injector).createWeatherSubComponent().inject(this)

        selectedWeatherUseCase = when ("AMSWeather") {
            "OWWeather" -> owWeatherUseCase
            "WAWeather" -> waWeatherUseCase
            "AMSWeather" -> amsWeatherUseCase
            else -> throw IllegalArgumentException("Invalid condition")
        }

        weatherViewModel = ViewModelProvider(
            this,
            WeatherViewModelFactory(selectedWeatherUseCase)
        )[WeatherViewModel::class.java]

        weatherViewModel.getCurrentWeather(35.0, 139.0, "metric")
        weatherViewModel.currentWeather.observe(this) { response ->
            Log.d("Response", response.toString())
        }
        Toast.makeText(applicationContext, "Hello", Toast.LENGTH_SHORT).show()
    }
}