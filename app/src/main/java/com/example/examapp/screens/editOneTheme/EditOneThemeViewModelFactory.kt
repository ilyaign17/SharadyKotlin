package com.example.examapp.screens.editOneTheme

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examapp.database.WordsDataBaseDao
import com.example.examapp.screens.editThemes.EditAllThemesViewModel

class EditOneThemeViewModelFactory(
    private val dao: WordsDataBaseDao,
    private val application: Application,
    private val args: EditOneThemeFragmentArgs
) : ViewModelProvider.Factory  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditOneThemeViewModel::class.java)) {
            return EditOneThemeViewModel(dao, application, args) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}