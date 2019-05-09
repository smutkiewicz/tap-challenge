package smutkiewicz.dafttapchallenge.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class ViewAnimator(private val view: View) {

    private var animator: ObjectAnimator? = null

    fun animate(scaleX: Float = 1.2f, scaleY: Float = 1.2f, duration: Long = 400) {
        animator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat("scaleX", scaleX),
            PropertyValuesHolder.ofFloat("scaleY", scaleY)
        )

        animator?.apply {
            this.duration = duration
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = FastOutSlowInInterpolator()
            start()
        }
    }

    fun cancel() {
        animator?.reverse()
        animator?.end()
        animator = null
    }
}