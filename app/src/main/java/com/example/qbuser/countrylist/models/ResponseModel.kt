package com.example.qbuser.countrylist.models

open class BaseResponse {
    val errors: List<Any?>? = null
    val status: String? = null
    val uid: String? = null
}

data class CountryResponse(val data: Data?): BaseResponse() {

    data class Data(val countries: List<Country?>?, val other_countries: List<Country?>?)

    data class Country(
        val arab_name: String?,
        val code: String?,
        val fars_name: String?,
        val icon: String?,
        val id: Int?,
        val index: Int?,
        val isd_code: String?,
        val iso_code_3: String?,
        val name: String?,
        val y_position: Int?
    )
}
