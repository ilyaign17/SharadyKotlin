package com.example.examapp.screens.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examapp.database.WordsDataBaseDao
import com.example.examapp.screens.choice.ChoiceOptionsViewModel

class GameFactory (
    private val dao: WordsDataBaseDao,
    private val application: Application,
    private val args: GameFragmentArgs
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(dao, application, args) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}