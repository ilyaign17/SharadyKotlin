package com.example.examapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordsDataBaseDao {
    
    @Insert
    fun insertWord(word: Word)

    @Insert
    fun insertTheme(theme: Theme)

    @Query("SELECT word_text FROM words_table WHERE theme_id = :key")
    fun getByID(key: Long): List<String>

    @Query("SELECT * FROM words_table WHERE theme_id = :key")
    fun getWordByID(key: Long): LiveData<List<Word>>

    @Query("SELECT word_text FROM words_table WHERE word_id = :key")
    fun getWord(key: Long): LiveData<String>

    @Query("SELECT word_text FROM words_table")
    fun getAllWords(): LiveData<List<String>>

    @Query("SELECT * FROM themes_table")
    fun getAllThemes() : LiveData<List<Theme>>

    @Query("SELECT MAX(theme_id) FROM themes_table")
    fun getMaxIdTheme() : Int

    @Query("SELECT MAX(word_id) FROM words_table")
    fun getMaxIdWord() : Int

    @Query("DELETE FROM themes_table WHERE theme_id = :key")
    fun deleteThemeById(key: Long)

    @Query("DELETE FROM words_table WHERE theme_id = :key")
    fun deleteWordsById(key: Long)

    @Query("DELETE FROM words_table WHERE word_id = :key")
    fun deleteOneWordById(key: Long)
}