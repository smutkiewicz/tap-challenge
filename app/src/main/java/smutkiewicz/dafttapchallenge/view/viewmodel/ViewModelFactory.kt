package smutkiewicz.dafttapchallenge.view.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import smutkiewicz.dafttapchallenge.database.Scores
import smutkiewicz.dafttapchallenge.database.ScoresDatabase
import smutkiewicz.dafttapchallenge.database.ScoresRepository

class ViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val scores: Scores

    init {
        ScoresDatabase.initIfNeeded(application)
        scores = ScoresRepository(ScoresDatabase.INSTANCE.scoresDao())
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(scores) as T
            modelClass.isAssignableFrom(GameViewModel::class.java) -> GameViewModel(scores) as T
            else -> throw IllegalArgumentException("Unknown view model ${modelClass.simpleName}")
        }
    }
}