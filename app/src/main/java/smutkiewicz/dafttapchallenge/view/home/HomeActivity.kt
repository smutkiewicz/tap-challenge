package smutkiewicz.dafttapchallenge.view.home

import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import smutkiewicz.dafttapchallenge.R
import smutkiewicz.dafttapchallenge.util.IntentActionHelper
import smutkiewicz.dafttapchallenge.view.game.GameActivity
import smutkiewicz.dafttapchallenge.view.scores.ScoresAdapter
import smutkiewicz.dafttapchallenge.view.viewmodel.HomeViewModel
import smutkiewicz.dafttapchallenge.view.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private val viewModel by lazy(::provideViewModel)
    private val adapter by lazy { ScoresAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupWindowAnimations()

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupButton()
        setupHighScores()
    }

    private fun setupWindowAnimations() {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition = Fade()
            enterTransition = Fade()
        }
    }

    private fun setupButton() {
        playBtn.setOnClickListener { IntentActionHelper.startActivityWithTransitionFrom(this, GameActivity::class.java) }
    }

    private fun setupHighScores() {
        topScoresRv.apply {
            adapter = this@HomeActivity.adapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            isNestedScrollingEnabled = false
        }

        GlobalScope.launch {
            adapter.items = viewModel.getTopFiveScores()
        }
    }

    private fun provideViewModel() = ViewModelProviders
        .of(this, ViewModelFactory(application))
        .get(HomeViewModel::class.java)
}
