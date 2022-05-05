package com.example.examapp.screens.choice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.examapp.database.Theme
import com.example.examapp.database.WordsDataBase
import com.example.examapp.database.WordsDataBaseDao
import kotlinx.coroutines.*

class ChoiceOptionsViewModel(
    private val dao: WordsDataBaseDao,
    application: Application
) : AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var themes = dao.getAllThemes()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}