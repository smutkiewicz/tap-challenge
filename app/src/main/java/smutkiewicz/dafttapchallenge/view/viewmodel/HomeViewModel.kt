package smutkiewicz.dafttapchallenge.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import smutkiewicz.dafttapchallenge.database.Scores
import smutkiewicz.dafttapchallenge.entity.Score

class HomeViewModel(private val repository: Scores) : ViewModel() {

    init {
        Log.d("TAP", getTopFiveScores().toString())
    }

    fun getTopFiveScores(): List<Score> {
        var topScores = emptyList<Score>()
        return repository.getTopFiveScores()
    }

}