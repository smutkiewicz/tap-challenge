package smutkiewicz.dafttapchallenge.util

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat

object IntentActionHelper {

    fun startActivityFrom(context: Context, clazz: Class<*>) = context.startActivity(Intent(context, clazz))

    fun startActivityWithTransitionFrom(activity: AppCompatActivity, clazz: Class<*>) = activity.startActivity(
        Intent(activity, clazz),
        ActivityOptionsCompat.makeCustomAnimation(activity, android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
    )
}