package com.example.shribhagwatgita.models

data class VersesItem(
    val chapter_number: Int,
    val commentaries: List<Commentary>,
    val id: Int,
    val text: String,
    val translations: List<Translation>,
    val verse_number: Int
)