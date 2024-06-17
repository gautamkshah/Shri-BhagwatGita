package com.example.shribhagwatgita.repositry

import com.example.shribhagwatgita.datasource.api.ApiUtilities
import com.example.shribhagwatgita.models.ChaptersItem
import com.example.shribhagwatgita.models.VersesItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository {

    fun getAllChapters() : Flow<List<ChaptersItem>> = callbackFlow{
        val callback=object : Callback<List<ChaptersItem>>{
            override fun onResponse(call: Call<List<ChaptersItem>>, response: Response<List<ChaptersItem>>) {
                if(response.isSuccessful && response.body()!=null){
                    trySend(response.body()!!)
                    close()

                }
            }

            override fun onFailure(call: Call<List<ChaptersItem>>, t: Throwable) {
                  close(t)
            }
        }
        ApiUtilities.api.getAllChapters().enqueue(callback)
        awaitClose {}
    }

    fun getVerses(chapterNumber: Int):Flow<List<VersesItem>> = callbackFlow {
        val callback = object : Callback<List<VersesItem>> {
            override fun onResponse(call: Call<List<VersesItem>>, response: Response<List<VersesItem>>) {
                if(response.isSuccessful && response.body()!=null){
                    trySend(response.body()!!)
                    close()
                }
            }

            override fun onFailure(call: Call<List<VersesItem>>, t: Throwable) {
                close(t)
            }
        }
        ApiUtilities.api.getVerses(chapterNumber).enqueue(callback)
        awaitClose {}
    }

    fun getParticularVerse(chapterNumber: Int, verseNumber: Int): Flow<VersesItem> = callbackFlow {
        val callback = object : Callback<VersesItem> {
            override fun onResponse(call: Call<VersesItem>, response: Response<VersesItem>) {
                if(response.isSuccessful && response.body()!=null){
                    trySend(response.body()!!)
                    close()
                }
            }

            override fun onFailure(call: Call<VersesItem>, t: Throwable) {
                close(t)
            }
        }
        ApiUtilities.api.getParticularVerse(chapterNumber, verseNumber).enqueue(callback)
        awaitClose {}
    }
}

