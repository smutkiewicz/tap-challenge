package smutkiewicz.dafttapchallenge.view.viewmodel

import androidx.lifecycle.ViewModel
import smutkiewicz.dafttapchallenge.database.Scores

class HomeViewModel(private val repository: Scores) : ViewModel() {

    suspend fun getTopFiveScores() = repository.getTopFiveScores()

}