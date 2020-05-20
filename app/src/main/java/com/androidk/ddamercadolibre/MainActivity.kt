package com.androidk.ddamercadolibre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.androidk.ddamercadolibre.data.api.MercadolibreAPI
import com.androidk.ddamercadolibre.data.model.listProducts
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Callback<listProducts> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




       searchProduct?.setOnKeyListener((View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                searchListProduct(searchProduct.text.toString())
                return@OnKeyListener true
            }
            false
        }))
    }

    private fun searchListProduct(product:String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var mercadoLibreAPI : MercadolibreAPI = retrofit.create(MercadolibreAPI::class.java)

        val listProducts : Call<listProducts> = mercadoLibreAPI.getProductsByDescription( product )

        listProducts.enqueue(this)

    }

    override fun onFailure(call: Call<listProducts>, t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<listProducts>, response: Response<listProducts>) {
        var products = response.body()
        if(response.isSuccessful){

            products?.results?.map { product ->
                Log.i("Test" , "Funciono!")
                Log.i("Test" , "${product.currency_id}\n")
                Log.i("Test" , "${product.id}\n")
                Log.i("Test" , "${product.price}\n")
                Log.i("Test" , "${product.thumbnail}\n")
                Log.i("Test" , "${product.title}\n")
            }

        }
    }
}

