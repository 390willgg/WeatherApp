package com.example.weatherappsample1yt.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappsample1yt.activity.MainActivity
import com.example.weatherappsample1yt.databinding.CityViewholderBinding
import com.example.weatherappsample1yt.model.open_weather.CityResponseApi_OpenWeather

class CityAdapter: RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private lateinit var binding: CityViewholderBinding
    inner class CityViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CityViewholderBinding.inflate(inflater, parent, false)
        return CityViewHolder()
    }

    override fun onBindViewHolder(holder: CityAdapter.CityViewHolder, position: Int) {
        val binding = CityViewholderBinding.bind(holder.itemView)
        binding.cityText.text = differ.currentList[position].name

        binding.cityText.setOnClickListener {
            Toast.makeText(binding.root.context, "Chip clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(binding.root.context, MainActivity::class.java)
            intent.putExtra("lat", differ.currentList[position].lat)
            intent.putExtra("lon", differ.currentList[position].lon)
            intent.putExtra("name", differ.currentList[position].name)
            binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private  val differCallBack = object : DiffUtil.ItemCallback<CityResponseApi_OpenWeather.CityResponseApiItem>() {
        override fun areItemsTheSame(oldItem: CityResponseApi_OpenWeather.CityResponseApiItem, newItem: CityResponseApi_OpenWeather.CityResponseApiItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CityResponseApi_OpenWeather.CityResponseApiItem, newItem: CityResponseApi_OpenWeather.CityResponseApiItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}