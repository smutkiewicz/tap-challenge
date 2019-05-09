package smutkiewicz.dafttapchallenge.view.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import smutkiewicz.dafttapchallenge.database.Scores
import smutkiewicz.dafttapchallenge.entity.Score
import smutkiewicz.dafttapchallenge.util.DateTimeHelper

class GameViewModel(private val repository: Scores) : ViewModel() {

    private var score = Score(position = 1, amountOfTaps = 0, timestamp = provideTimestamp())

    // property to block game from restarting when user presses Home after getting AlertDialog with result
    private var gameFinished = false

    fun provideSecondsMillisAndColorText(millisUntilFinished: Long): Triple<String, String, Int> {
        val seconds = millisUntilFinished / 1000
        val millis = (millisUntilFinished % 1000) / 10
        val millisZeroChar = if (millis < 10) "0" else ""
        val color = if (seconds < 2) Color.RED else Color.WHITE

        return Triple("0$seconds", "$millisZeroChar$millis", color)
    }

    fun provideSecondsText(millisUntilFinished: Long) = (millisUntilFinished / 1000 + 1).toString()

    fun provideTimestamp() = DateTimeHelper.getCurrentDateTimeStamp()

    fun setTapsAndFinishGame(taps: Int) {
        score.amountOfTaps += taps
        gameFinished = true
    }

    suspend fun addToHighScores() = repository.add(score)

    fun obtainResult() = score.amountOfTaps

    fun checkIfGameIsFinished() = gameFinished
}