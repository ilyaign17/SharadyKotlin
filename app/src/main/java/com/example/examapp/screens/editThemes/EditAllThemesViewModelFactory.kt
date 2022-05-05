package com.example.examapp.screens.editThemes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examapp.database.WordsDataBaseDao
import com.example.examapp.screens.choice.ChoiceOptionsViewModel

class EditAllThemesViewModelFactory(
    private val dao: WordsDataBaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditAllThemesViewModel::class.java)) {
            return EditAllThemesViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}