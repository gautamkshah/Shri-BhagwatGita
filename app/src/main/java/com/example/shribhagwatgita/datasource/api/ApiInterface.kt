package com.example.shribhagwatgita.datasource.api

import com.example.shribhagwatgita.models.ChaptersItem
import com.example.shribhagwatgita.models.VersesItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {

    @GET("/v2/chapters/")
    fun getAllChapters() : Call<List<ChaptersItem>>



    @GET("/v2/chapters/{chapterNumber}/verses/")
    fun getVerses(@Path("chapterNumber") chapterNumber: Int) : Call<List<VersesItem>>


    @GET("/v2/chapters/{chapterNum}/verses/{verseNumb}")
    fun getParticularVerse(@Path("chapterNum") chapterNumber: Int, @Path("verseNumb") verseNumber: Int) : Call<VersesItem>
}