package com.example.shribhagwatgita.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shribhagwatgita.datasource.api.room.AppDatabase
import com.example.shribhagwatgita.datasource.api.room.SavedChapters
import com.example.shribhagwatgita.datasource.api.room.SavedVerses
import com.example.shribhagwatgita.models.ChaptersItem
import com.example.shribhagwatgita.models.VersesItem
import com.example.shribhagwatgita.repositry.AppRepository
import kotlinx.coroutines.flow.Flow







class MainViewModel(application: Application):AndroidViewModel(application) {

    val savedChaptersDao = AppDatabase.getDatabaseInatance(application).savedChaptersDao()
    val savedVersesDao = AppDatabase.getDatabaseInatance(application).savedVersesDao()
    val appRepository = AppRepository(savedChaptersDao!!, savedVersesDao!!)
    fun getAllChapters():Flow<List<ChaptersItem>>  = appRepository.getAllChapters()
    fun getVerses(chapterNumber: Int):Flow<List<VersesItem>> = appRepository.getVerses(chapterNumber)


    fun getParticularVerse(chapterNumber: Int, verseNumber: Int): Flow<VersesItem> = appRepository.getParticularVerse(chapterNumber, verseNumber)

    // saved chapters
    suspend fun insertChapter(savedChapters: SavedChapters) = appRepository.insertChapter(savedChapters)

    fun getSavedChapters(): LiveData<List<SavedChapters>> = appRepository.getSavedChapters()


    fun getParticularChapter(chapterNumber:Int): LiveData<SavedChapters> = appRepository.getParticularChapter(chapterNumber)

    suspend fun deleteChapter(chapterNumber: Int) = appRepository.deleteChapter(chapterNumber)


    suspend fun insertEnglishVerse(versesInEnglish: SavedVerses) = appRepository.insertEnglishVerse(versesInEnglish)
    fun getSavedEnglishVerses(): LiveData<List<SavedVerses>> = appRepository.getSavedEnglishVerses()


    fun getParticularEnglishVerse(chapterNumber: Int, verseNumber: Int): LiveData<SavedVerses> = appRepository.getParticularEnglishVerse(chapterNumber, verseNumber)

    suspend fun deleteParticularVerse(chapterNumber: Int, verseNumber: Int) = appRepository.deleteParticularVerse(chapterNumber, verseNumber)


}