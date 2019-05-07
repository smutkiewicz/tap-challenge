package smutkiewicz.dafttapchallenge.view.game

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import smutkiewicz.dafttapchallenge.R
import smutkiewicz.dafttapchallenge.util.ViewAnimator
import smutkiewicz.dafttapchallenge.view.viewmodel.GameViewModel
import smutkiewicz.dafttapchallenge.view.viewmodel.ViewModelFactory


class GameActivity : AppCompatActivity(), GameTouchCounterView.TouchCounter {

    private val viewModel by lazy(::provideViewModel)
    private val viewAnimator by lazy { ViewAnimator() }

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        prepareGame()
    }

    override fun onPause() {
        super.onPause()
        cancelGame()
    }

    override fun onTouchCount(touchCount: Int) {
        tapsTv.text = getString(R.string.taps, touchCount)
    }

    private fun prepareGame() {
        tapsTv.text = getString(R.string.get_ready)
        timerTv.text = getString(R.string.sample_game_time)

        launchPrepareCountDownTimer()
    }

    private fun updatePrepareTimer(millisUntilFinished: Long) {
        counterTv.text = viewModel.provideSecondsText(millisUntilFinished)
    }

    private fun startGame() {
        tapsTv.text = getString(R.string.taps, 0)
        counterTv.text = getString(R.string.play)

        gameAreaView.apply {
            touchCounter = this@GameActivity
            isTouchCountEnabled = true
        }

        viewAnimator.animate(tapIv)
        launchGameCountDownTimer()
    }

    private fun updateGameTimer(millisUntilFinished: Long) {
        val timerText = viewModel.provideSecondsMillisText(millisUntilFinished)
        timerTv.text = getString(R.string.game_time, timerText.first, timerText.second)
    }

    private fun cancelGame() {
        gameAreaView.apply {
            isTouchCountEnabled = false
            touchCounter = null
            cancelTouchCount()
        }

        viewAnimator.cancel()
        timer.cancel()
    }

    private fun finishGame() {
        gameAreaView.apply {
            isTouchCountEnabled = false
            touchCounter = null
        }

        viewAnimator.cancel()
        viewModel.setTaps(gameAreaView.touchCount)

        // handle High Scores
        GlobalScope.async {
            val isMyScoreAddedAsHighScore = viewModel.addToHighScores()
            withContext(Dispatchers.Main) {
                showResultAlertDialog(isMyScoreAddedAsHighScore)
            }
        }
    }

    private fun launchPrepareCountDownTimer() {
        timer = object : CountDownTimer(3000, 1000) {

            override fun onTick(millisUntilFinished: Long) = updatePrepareTimer(millisUntilFinished)

            override fun onFinish() = startGame()

        }.start()
    }

    private fun launchGameCountDownTimer() {
        timer = object : CountDownTimer(5000, 100) {

            override fun onTick(millisUntilFinished: Long) = updateGameTimer(millisUntilFinished)

            override fun onFinish() {
                updateGameTimer(0)
                finishGame()
            }
        }.start()
    }

    private fun showResultAlertDialog(isMyScoreHighScore: Boolean) {
        AlertDialog.Builder(this@GameActivity)
            .apply {
                setTitle(if(isMyScoreHighScore) getString(R.string.well_done_new_record) else getString(R.string.your_score))
                setMessage(getString(R.string.you_ve_achieved_taps, viewModel.obtainResult()))
                setIcon(R.drawable.ic_tap)
                setPositiveButton(R.string.ok) { _, _ -> onNavigateUp() }
                setCancelable(false)
                create()
                show()
            }
    }

    private fun provideViewModel() = ViewModelProviders
        .of(this, ViewModelFactory(application))
        .get(GameViewModel::class.java)
}
