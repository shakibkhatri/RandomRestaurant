package com.example.randomrestaurant

import android.app.DownloadManager.Request
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.randomrestaurant.API.ApiInterface
import com.example.randomrestaurant.API.RetrofitObject
import com.example.randomrestaurant.databinding.ActivityMainBinding
import com.example.randomrestaurant.model.ApiResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag
import retrofit2.http.Url

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        nextRestaurant()

    }

    private fun getData() {

        val restaurant = RetrofitObject.apiInterface.getRestaurant("restaurant")

        restaurant.enqueue(object: Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                val rest = response.body()
                if (rest != null){
                    Glide.with(this@MainActivity).load("https://loremflickr.com/500/500/restaurant").fitCenter().into(binding.ivLogo)
                    binding.tvResName.text = rest.name
                    binding.tvResType.text = rest.type
                    binding.tvResDetails.text = rest.description
                    binding.tvResReviews.text = rest.review
                    binding.tvPhoneNumber.text = rest.phoneNumber
                    binding.tvResAddress.text = rest.address

                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Some Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun nextRestaurant(){
        binding.btnNext.setOnClickListener {
            getData()
        }
    }
}


