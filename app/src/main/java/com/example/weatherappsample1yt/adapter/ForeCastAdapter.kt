package com.example.weatherappsample1yt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherappsample1yt.R
import com.example.weatherappsample1yt.databinding.ForecastViewholderBinding
import com.example.weatherappsample1yt.model.open_weather.ForecastResponseApi_OpenWeather
import java.text.SimpleDateFormat
import java.util.*

class ForeCastAdapter : RecyclerView.Adapter<ForeCastAdapter.ForeCastViewHolder>() {
    private lateinit var binding: ForecastViewholderBinding
    private var timeZone: Int? = null

    inner class ForeCastViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ForeCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ForecastViewholderBinding.inflate(inflater, parent, false)
        return ForeCastViewHolder()
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        val binding = ForecastViewholderBinding.bind(holder.itemView)
        val timeZone = TimeZone.getTimeZone(Locale.getDefault().toString())

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone

        val date = dateFormat.parse(differ.currentList[position].dtTxt.toString())
        val calendar = Calendar.getInstance(timeZone)
        calendar.timeInMillis = (date ?: Date()).time

        val currentDateTime = Calendar.getInstance(timeZone)

        val outputFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        val dayOfWeek = date?.let { outputFormat.format(it) }

        // Jika tanggal data sama dengan tanggal saat ini
        if (calendar.get(Calendar.DAY_OF_YEAR) == currentDateTime.get(Calendar.DAY_OF_YEAR) &&
            calendar.get(Calendar.YEAR) == currentDateTime.get(Calendar.YEAR)
        ) {
            // Tampilkan "Today" sebagai judul
            binding.titleText.text = "Today"
        } else {
            // Tampilkan tanggal sebagai judul
            binding.titleText.text = dayOfWeek
        }

        // Jika waktu data sama atau setelah waktu saat ini
        if (!calendar.time.before(currentDateTime.time)) {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            Log.d("hour", hour.toString())
            val timeType = if (hour < 12) "AM" else "PM"
            val hour12 = calendar.get(Calendar.HOUR)
            Log.d("hour12", hour12.toString())

            val icon = when (differ.currentList[position].weather?.get(0)?.icon.toString()) {
                "01d", "0n" -> "sunny"
                "02d", "02n" -> "cloudy_sunny"
                "03d", "03n" -> "cloudy_sunny"
                "04d", "04n" -> "cloudy"
                "09d", "09n" -> "rainy"
                "10d", "10n" -> "rainy"
                "11d", "11n" -> "storm"
                "13d", "13n" -> "snow"
                "50d", "50n" -> "mist"
                else -> "sunny"
            }

            // Create a map of icon names to drawable resource IDs
            val iconMap: Map<String, Int> = mapOf(
                "sunny" to R.drawable.sunny_icon,
                "cloudy_sunny" to R.drawable.cloudy_sunny,
                "cloudy" to R.drawable.cloudy,
                "rainy" to R.drawable.rainy,
                "storm" to R.drawable.storm,
                "snow" to R.drawable.snowy,
                "mist" to R.drawable.haze
            )
            val drawableResourceId = iconMap[icon]

            binding.apply {
                hourText.text = holder.itemView.context.getString(
                    R.string.temperatur_format, hour12, timeType
                )
                tempText.text = differ.currentList[position].main?.temp?.let {
                    Math.round(it).toString() + "°"
                }

                Glide.with(root.context).load(drawableResourceId).into(pic)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun setTimezone(timezone: Int) {
        val oldTimezone = this.timeZone
        this.timeZone = timezone
        if (oldTimezone != timezone) {
            notifyItemChanged(timezone)
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ForecastResponseApi_OpenWeather.Data>() {
        override fun areItemsTheSame(
            oldItem: ForecastResponseApi_OpenWeather.Data, newItem: ForecastResponseApi_OpenWeather.Data
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastResponseApi_OpenWeather.Data, newItem: ForecastResponseApi_OpenWeather.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}