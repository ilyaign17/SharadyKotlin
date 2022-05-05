package com.example.examapp.screens.editThemes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.examapp.database.Theme
import com.example.examapp.database.WordsDataBaseDao
import kotlinx.coroutines.*

class EditAllThemesViewModel (
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

    fun addTheme(name : String){
        uiScope.launch {
            addThemeCor(name)
        }
    }

    private suspend fun addThemeCor(name : String){
        withContext(Dispatchers.IO) {
            var addId = dao.getMaxIdTheme() + 1
            dao.insertTheme(Theme(addId.toLong(), name))
        }
    }

    fun deleteTheme(id : Long){
        uiScope.launch {
            deleteThemeCor(id)
        }
    }

    private suspend fun deleteThemeCor(id : Long){
        withContext(Dispatchers.IO) {
            dao.deleteThemeById(id)
            dao.deleteWordsById(id)
        }
    }

}