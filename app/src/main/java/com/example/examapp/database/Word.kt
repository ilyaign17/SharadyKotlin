package com.example.examapp.database

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "words_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word_id")
    var wordID: Long = 0L,

    @ColumnInfo(name = "word_text")
    var wordText: String = "",

    @ColumnInfo(name = "theme_id")
    var themeID: Long = 0L
)
