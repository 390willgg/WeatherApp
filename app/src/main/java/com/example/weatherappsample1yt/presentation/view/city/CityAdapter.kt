package com.example.weatherappsample1yt.presentation.view.city

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappsample1yt.data.model.format.DataItemCity
import com.example.weatherappsample1yt.databinding.CityViewholderBinding
import com.example.weatherappsample1yt.presentation.view.main.MainActivity

class CityAdapter: RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private lateinit var binding: CityViewholderBinding
    inner class CityViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CityViewholderBinding.inflate(inflater, parent, false)
        return CityViewHolder()
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val binding = CityViewholderBinding.bind(holder.itemView)
        val city = differ.currentList[position]
        binding.apply {
            cityText.text = city.cityName

            cityText.setOnClickListener {
                Toast.makeText(root.context, "Chip clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(root.context, MainActivity::class.java)
                intent.putExtra("lat", city.latitude)
                intent.putExtra("lon", city.longitude)
                intent.putExtra("name", city.cityName)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallBack = object : DiffUtil.ItemCallback<DataItemCity>() {
        override fun areItemsTheSame(
            oldItem: DataItemCity, newItem: DataItemCity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DataItemCity,
            newItem: DataItemCity
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
}