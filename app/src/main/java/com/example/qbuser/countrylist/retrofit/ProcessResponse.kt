package com.example.qbuser.countrylist.retrofit
import com.example.qbuser.countrylist.adapators.CountryAdaptor
import com.example.qbuser.countrylist.models.CountryResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProcessResponse(var retrofitCallObj: Call<Any>){

    var t1:Throwable? = null
    var r:Response<Any>? = null

    fun getResponse(){
        retrofitCallObj.enqueue(object : Callback<Any>{
            override fun onFailure(call: Call<Any>, t: Throwable) {
                t1 = t
            }

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                r = response

            }
        })
    }
}