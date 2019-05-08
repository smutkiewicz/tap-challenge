package smutkiewicz.dafttapchallenge.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {
    private const val DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss"

    private val simpleDateFormatter: SimpleDateFormat = SimpleDateFormat(DATE_TIME_FORMAT)

    fun getCurrentDateTimeStamp()= simpleDateFormatter.format(Date())
}