package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.api


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {


// companion as static in java public static Retrofit getRetrofitInstance

    companion object {
        private const val BASE_URL = "http://173.249.1.117:8095/van.dll/"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}