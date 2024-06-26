package com.example.shribhagwatgita.datasource.api.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedChaptersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(savedChapters:SavedChapters)

    @Query("SELECT * FROM SavedChapters")
    fun getSavedChapters():LiveData<List<SavedChapters>>

    @Query("DELETE FROM SavedChapters WHERE chapter_number = :chapterNumber")
    suspend fun deleteChapter(chapterNumber:Int)

    @Query("SELECT * FROM SavedChapters WHERE chapter_number = :chapterNumber")
    fun getParticularChapter(chapterNumber:Int): LiveData<SavedChapters>
}

@Dao
interface SavedVersesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnglishVerse(versesInEnglish:SavedVerses)

    @Query("SELECT * FROM SavedVerses")
    fun getSavedEnglishVerses():LiveData<List<SavedVerses>>

    @Query("SELECT * FROM SavedVerses WHERE chapter_number = :chapterNumber AND verse_number = :verseNumber")
    fun getParticularEnglishVerse(chapterNumber:Int, verseNumber:Int): LiveData<SavedVerses>

    @Query("DELETE FROM SavedVerses WHERE chapter_number = :chapterNumber AND verse_number = :verseNumber")
    suspend fun deleteParticularVerse(chapterNumber:Int, verseNumber:Int)
}