package com.example.examapp.screens.game

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.examapp.database.Word
import com.example.examapp.database.WordsDataBaseDao
import kotlinx.coroutines.*

class GameViewModel(
    private val dao: WordsDataBaseDao,
    application: Application,
    args: GameFragmentArgs
) : AndroidViewModel(application) {

    val timer: CountDownTimer

    // The current word
    var word = ""

    // The current score
    var score = 0

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main)

    companion object {
        // These represent different important times in the game, such as game length.

        // This is when the game is over
        private const val DONE = 0L

        // This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        // This is the total time of the game
//        private const val COUNTDOWN_TIME = 60000L

    }
    var COUNTDOWN_TIME = args.time * 1000
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init{
        getWordsById(args.choiceThemeId.toLong())

        timer = object : CountDownTimer(COUNTDOWN_TIME.toLong(), ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }
        }

        timer.start()

    }

    fun getWordsById(key: Long){
        uiScope.launch {
            getWordsByIdCor(key)

        }
    }

    private suspend fun getWordsByIdCor(key: Long){
        withContext(Dispatchers.IO) {
            wordList = dao.getByID(key).toMutableList()
//            wordList.shuffle()
            nextWord()

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        timer.cancel()
    }


    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _eventGameFinish.value = true
//            gameFinished()
        } else {
            word = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score--
        if (score < 0){
            score = 0
        }
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

}