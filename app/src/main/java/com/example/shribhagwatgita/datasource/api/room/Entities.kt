package com.example.shribhagwatgita.datasource.api.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shribhagwatgita.models.Commentary
import com.example.shribhagwatgita.models.Translation


@Entity(tableName = "SavedChapters")
    data class SavedChapters(
        @PrimaryKey
        val chapter_number: Int,
        val chapter_summary: String,
        val id: Int,
        val name: String,
        val name_meaning: String,
        val name_translated: String,
        val name_transliterated: String,
        val verses_count: Int,

    )


@Entity(tableName = "SavedVerses")
data class SavedVerses(

    val chapter_number: Int,
    val commentaries: List<Commentary>,
    @PrimaryKey
    val id: Int,
    val text: String,
    val translations: List<Translation>,
    val verse_number: Int
)
