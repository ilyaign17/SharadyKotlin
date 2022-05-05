package com.example.examapp.screens.choice

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examapp.database.WordsDataBaseDao

class ChoiceOptionsViewModelFactory(
    private val dao: WordsDataBaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChoiceOptionsViewModel::class.java)) {
            return ChoiceOptionsViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}