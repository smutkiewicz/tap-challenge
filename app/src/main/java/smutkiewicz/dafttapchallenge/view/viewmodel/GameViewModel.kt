package smutkiewicz.dafttapchallenge.view.viewmodel

import androidx.lifecycle.ViewModel
import smutkiewicz.dafttapchallenge.database.Scores
import smutkiewicz.dafttapchallenge.entity.Score
import smutkiewicz.dafttapchallenge.util.DateTimeHelper

class GameViewModel(private val repository: Scores) : ViewModel() {

    private var score = Score(position = 1, amountOfTaps = 0, timestamp = provideTimestamp())

    fun provideSecondsMillisText(millisUntilFinished: Long): Pair<String, String> {
        val seconds = millisUntilFinished / 1000
        val millis = (millisUntilFinished % 1000) / 10
        val millisZeroChar = if (millis < 10) "0" else ""

        return Pair("0$seconds", "$millisZeroChar$millis")
    }

    fun provideSecondsText(millisUntilFinished: Long) = (millisUntilFinished / 1000 + 1).toString()

    fun provideTimestamp() = DateTimeHelper.getCurrentDateTimeStamp()

    fun setTaps(taps: Int) {
        score.amountOfTaps += taps
    }

    fun addToTopScores(): Boolean {
        var isMyScoreHighScore = false
        isMyScoreHighScore = repository.add(score)

        return isMyScoreHighScore
    }

    fun obtainResult() = score.amountOfTaps
}