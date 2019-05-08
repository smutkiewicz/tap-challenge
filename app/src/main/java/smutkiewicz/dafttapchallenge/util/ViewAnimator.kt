package smutkiewicz.dafttapchallenge.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class ViewAnimator {

    private var animator: ObjectAnimator? = null

    fun animate(view: View) {
        animator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )

        animator?.apply {
            duration = 400
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = FastOutSlowInInterpolator()
            start()
        }
    }

    fun cancel() {
        animator?.cancel()
        animator = null
    }
}