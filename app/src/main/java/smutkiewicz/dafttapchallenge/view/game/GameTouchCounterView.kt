package smutkiewicz.dafttapchallenge.view.game

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class GameTouchCounterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    var touchCounter: TouchCounter? = null
    var isTouchCountEnabled: Boolean = false
    var touchCount: Int = 0
        private set

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                countTouch()
            }
        }

        return super.dispatchTouchEvent(event)
    }

    fun cancelTouchCount() {
        touchCount = 0
    }

    private fun countTouch() {
        if (isTouchCountEnabled) {
            touchCount++
            touchCounter?.onTouchCount(touchCount)
        }
    }

    interface TouchCounter {

        fun onTouchCount(touchCount: Int)

    }
}