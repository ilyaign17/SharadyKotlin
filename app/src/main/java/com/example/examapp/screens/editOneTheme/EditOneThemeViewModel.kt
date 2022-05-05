package com.example.examapp.screens.editOneTheme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.examapp.database.Theme
import com.example.examapp.database.Word
import com.example.examapp.database.WordsDataBaseDao
import kotlinx.coroutines.*

class EditOneThemeViewModel(
    private val dao: WordsDataBaseDao,
    application: Application,
    args: EditOneThemeFragmentArgs
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var words = dao.getWordByID(args.editThemeId.toLong())

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun addWord(name : String, idTheme : Long){
        uiScope.launch {
            addWordCor(name, idTheme)
        }
    }

    private suspend fun addWordCor(name : String, idTheme : Long){
        withContext(Dispatchers.IO) {
            var addId = dao.getMaxIdWord() + 1
            dao.insertWord(Word(addId.toLong(), name, idTheme))
        }
    }

    fun deleteWord(id : Long){
        uiScope.launch {
            deleteWordCor(id)
        }
    }

    private suspend fun deleteWordCor(id : Long){
        withContext(Dispatchers.IO) {
            dao.deleteOneWordById(id)
        }
    }
}