package com.example.qbuser.countrylist.retrofit
import com.example.qbuser.countrylist.models.CountryResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class SimpleResponse {
    var status: Int = 0
    var message: String? = null
    fun isSuccess() = status==200
}

sealed class ApiResult<out T: Any>
data class Success<out T: Any>(val contents: T): ApiResult<T>()
data class Failure(val errorCode: Int, val throwable: Throwable): ApiResult<Nothing>()


class DhowberApiHelper(){
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://dhowberbackend.qburst.build/")
        .build()
    val retrofitServiceObj = retrofit.create(RetrofitService::class.java)


    fun getCountryDetails(): ApiResult<CountryResponse> = retrofitServiceObj.getAllCountries().ProcessResponse<CountryResponse>()

}



fun <T: Any>Call<T>.ProcessResponse(): ApiResult<T>{
    return try {

        val response = execute()

        if(response.isSuccessful && response.body() != null){

            Success(response.body() as T)

        } else {

            val errorBody = response.errorBody()?.string()
            println("*******************************************************")
            println(errorBody)
            println("................................................................")
            val errorResponse = Gson().fromJson(errorBody, SimpleResponse::class.java)

            val errorMessage = errorResponse?.message ?: "Code:${response.code()} - Empty response."
            Failure(response.code(), Throwable(errorMessage))
        }

    } catch (e: Exception) {
        println("++++++++++++++++++++++++++++++++++++++++++++++++")
        println(e)
        println("-------------------------------------------------------")
        Failure(-1, Throwable(e.localizedMessage))
    }



}