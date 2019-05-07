package smutkiewicz.dafttapchallenge.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.widget.ImageView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator


class ViewAnimator {

    private var scaleDown: ObjectAnimator? = null

    fun animate(imageView: ImageView) {
        scaleDown = ObjectAnimator.ofPropertyValuesHolder(
            imageView,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )

        scaleDown?.apply {
            duration = 400
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = FastOutSlowInInterpolator()
            start()
        }
    }

    fun cancel() = scaleDown?.cancel()
}