package smutkiewicz.dafttapchallenge.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import smutkiewicz.dafttapchallenge.R
import smutkiewicz.dafttapchallenge.util.IntentActionHelper
import smutkiewicz.dafttapchallenge.view.scores.ScoresAdapter
import smutkiewicz.dafttapchallenge.view.viewmodel.HomeViewModel
import smutkiewicz.dafttapchallenge.view.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private val viewModel by lazy(::provideViewModel)
    private val adapter by lazy { ScoresAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupButton()
        setupHighScores()
    }

    private fun setupButton() {
        playBtn.setOnClickListener { IntentActionHelper.startGameActivity(this@HomeActivity) }
    }

    private fun setupHighScores() {
        topScoresRv.adapter = adapter
        topScoresRv.layoutManager = LinearLayoutManager(this)
        topScoresRv.isNestedScrollingEnabled = false

        adapter.items = viewModel.getTopFiveScores()
    }

    private fun provideViewModel() = ViewModelProviders
        .of(this, ViewModelFactory(application))
        .get(HomeViewModel::class.java)
}
