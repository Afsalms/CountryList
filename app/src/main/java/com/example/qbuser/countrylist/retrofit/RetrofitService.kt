package com.example.qbuser.countrylist.retrofit

import com.example.qbuser.countrylist.models.CountryResponse
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitService{

    @GET("countries")
    fun getAllCountries(): Call<CountryResponse>
}