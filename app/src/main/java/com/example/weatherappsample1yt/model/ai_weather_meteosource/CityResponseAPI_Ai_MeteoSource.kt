package com.example.weatherappsample1yt.model.ai_weather_meteosource


import com.google.gson.annotations.SerializedName

class CityResponseAPI_Ai_MeteoSource : ArrayList<CityResponseAPI_Ai_MeteoSource.CityResponseAPI_Ai_MeteoSourceItem>(){
    data class CityResponseAPI_Ai_MeteoSourceItem(
        @SerializedName("adm_area1")
        val admArea1: String?,
        @SerializedName("adm_area2")
        val admArea2: Any?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: String?,
        @SerializedName("lon")
        val lon: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("place_id")
        val placeId: String?,
        @SerializedName("timezone")
        val timezone: String?,
        @SerializedName("type")
        val type: String?
    )
}