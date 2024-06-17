package com.example.shribhagwatgita.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shribhagwatgita.models.ChaptersItem
import com.example.shribhagwatgita.models.VersesItem
import com.example.shribhagwatgita.repositry.AppRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {
    val appRepository = AppRepository()
    fun getAllChapters():Flow<List<ChaptersItem>>  = appRepository.getAllChapters()
    fun getVerses(chapterNumber: Int):Flow<List<VersesItem>> = appRepository.getVerses(chapterNumber)

    fun getParticularVerse(chapterNumber: Int, verseNumber: Int): Flow<VersesItem> = appRepository.getParticularVerse(chapterNumber, verseNumber)
}