package com.example.shribhagwatgita.datasource.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities{

    val headers= mapOf<String,String>(
        "Accept" to "application/json",
        "X-RapidAPI-Key" to "b5380045a2msh1b71b246aa30cc0p183221jsn2d8555a8b898",
        "X-RapidAPI-Host" to "bhagavad-gita3.p.rapidapi.com"
    )

    val client=OkHttpClient.Builder().apply {
        addInterceptor {
            val newrequest=it.request().newBuilder().apply{
                headers.forEach { (key, value) ->
                    addHeader(key,value)
                }
            }.build()

            it.proceed(newrequest)
        }
    }.build()

   val api: ApiInterface by lazy {
       Retrofit.Builder()
           .baseUrl("https://bhagavad-gita3.p.rapidapi.com")
           .client(client)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(ApiInterface::class.java)
    }
}