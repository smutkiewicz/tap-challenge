package smutkiewicz.dafttapchallenge.util

import android.content.Context
import android.content.Intent
import smutkiewicz.dafttapchallenge.view.game.GameActivity

object IntentActionHelper {

    fun startGameActivity(context: Context) = startActivityFrom(context, GameActivity::class.java)

    private fun startActivityFrom(context: Context, clazz: Class<*>) = context.startActivity(Intent(context, clazz))

}