package com.example.weatherappsample1yt.presentation.view.city

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class CityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

//    private lateinit var binding: ActivityCityBinding
//    private lateinit var cityViewModel: CityViewModel
//    private val cityAdapter by lazy { CityAdapter() }
//
//    companion object {
//        private const val TAG = "CityActivity"
//    }
//
//    private fun dpToPx(dp: Int, context: Context): Int {
//        return (dp * context.resources.displayMetrics.density).toInt()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        binding = ActivityCityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val cityRepository = getOWCityRepository()
//        val citiesListUseCase = CitiesListRemoteDataSourceImpl(repository = cityRepository)
//        cityViewModel = CityViewModel(citiesListUseCase)
//
//        window.apply {
//            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            statusBarColor = Color.TRANSPARENT
//        }
//
//        binding.apply {
//            cityEditText.addTextChangedListener(object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?, start: Int, count: Int, after: Int
//                ) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//                override fun afterTextChanged(s: android.text.Editable?) {
//                    progressBar2.visibility = View.VISIBLE
//                    CoroutineScope(Dispatchers.Main).launch {
////                        cityViewModel.loadCity(s.toString(), 10, { cityResponseApi ->
////                            // Handle successful response
////                            progressBar2.visibility = View.GONE
////                            cityAdapter.differ.submitList(cityResponseApi)
////                            cityView.apply {
////                                layoutManager = LinearLayoutManager(
////                                    this@CityActivity, LinearLayoutManager.HORIZONTAL, false
////                                )
////                                adapter = cityAdapter
////                            }
////                        }, { throwable ->
////                            // Handle error
////                            progressBar2.visibility = View.GONE
////                            // Log the error
////                            Log.e(TAG, "Error loading city data", throwable)
////                            // Show a toast with the error message
////                            Toast.makeText(this@CityActivity, throwable.message, Toast.LENGTH_LONG)
////                                .show()
////                            // Or show a dialog with the error message
////                            AlertDialog.Builder(this@CityActivity).setTitle("Error")
////                                .setMessage(throwable.message).setPositiveButton("OK", null).show()
////                        })
//                        cityViewModel.citiesList.observe(this@CityActivity) {
//                            progressBar2.visibility = View.GONE
//                            cityAdapter.differ.submitList(it.data)
//                            cityView.apply {
//                                layoutManager = LinearLayoutManager(
//                                    this@CityActivity, LinearLayoutManager.HORIZONTAL, false
//                                )
//                                adapter = cityAdapter
//                            }
//                        }
//                    }
//                }
//            })
//        }
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            val additionalPadding = dpToPx(16, v.context)
//            v.setPadding(
//                systemBars.left + additionalPadding,
//                systemBars.top + additionalPadding,
//                systemBars.right + additionalPadding,
//                systemBars.bottom + additionalPadding
//            )
//            insets
//        }
//    }
}
