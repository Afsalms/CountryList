package com.example.qbuser.countrylist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.qbuser.countrylist.adapators.CountryAdaptor
import com.example.qbuser.countrylist.models.CountryResponse
import com.example.qbuser.countrylist.retrofit.ProcessResponse
import com.example.qbuser.countrylist.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        country_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://dhowberbackend.qburst.build/")
            .build()
        val a = retrofit.create(RetrofitService::class.java)
        val response = a.getAllCountries()
//        response.enqueue(object : Callback<CountryResponse>{
//            override fun onResponse(call: Call<CountryResponse>, response: Response<CountryResponse>) {
//                val data = response!!.body()
//                var countries: List<CountryResponse.Country> = data?.data?.countries as List<CountryResponse.Country>
//                countries = countries?.plus(data?.data?.other_countries as List<CountryResponse.Country>)
//                var adaptor = CountryAdaptor(countries as List<CountryResponse.Country>)
//                country_list.adapter = adaptor
//            }
//
//            override fun onFailure(call: Call<CountryResponse>, t: Throwable) {
//                println("----------------------------------------")
//            }
//        })
        val test = ProcessResponse(response as Call<Any>)
        test.getResponse()
        var data = test.r?.body() as CountryResponse
        var countries: List<CountryResponse.Country> = data?.data?.countries as List<CountryResponse.Country>
        countries = countries?.plus(data?.data?.other_countries as List<CountryResponse.Country>)
        var adaptor = CountryAdaptor(countries as List<CountryResponse.Country>)
                country_list.adapter = adaptor
    }
}
