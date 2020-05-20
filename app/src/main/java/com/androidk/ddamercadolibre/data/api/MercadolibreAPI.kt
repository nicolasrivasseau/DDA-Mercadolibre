package com.androidk.ddamercadolibre.data.api

import com.androidk.ddamercadolibre.data.model.listProducts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadolibreAPI {
    @GET("sites/MLA/search")
    fun getProductsByDescription(@Query("q")productDescription:String): Call<listProducts>
}