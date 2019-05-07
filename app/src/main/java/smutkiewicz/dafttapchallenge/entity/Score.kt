package smutkiewicz.dafttapchallenge.entity

data class Score(
    val position: Int,
    var amountOfTaps: Int,
    val timestamp: String
)