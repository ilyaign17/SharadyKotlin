package com.example.examapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Theme::class, Word::class],
    version = 1,
    exportSchema = false)
abstract class WordsDataBase : RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE : WordsDataBase? = null

        fun getInstance(context: Context) : WordsDataBase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        WordsDataBase::class.java, "words_db")
                        .createFromAsset("database/words.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    abstract fun getWordsDataBaseDao() : WordsDataBaseDao
}