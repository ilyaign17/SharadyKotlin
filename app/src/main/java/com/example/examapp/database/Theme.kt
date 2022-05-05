package com.example.examapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "themes_table")
data class Theme(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "theme_id")
    var themeID: Long = 0L,

    @ColumnInfo(name = "name_theme")
    var nameTheme: String = ""
)