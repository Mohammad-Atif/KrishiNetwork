package com.example.krishinetwork.api

import com.example.krishinetwork.models.KrishiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KrishiApi {

    @GET("mandi")
    suspend fun getCopsDetails(
        @Query("lat")
        latitude:Double,
        @Query("lon")
        longitude:Double,
        @Query("ver")
        version:Int,
        @Query("lang")
        language:String,
        @Query("crop_id")
        cropId:Int
    ):Response<KrishiData>
}