package smutkiewicz.dafttapchallenge.database

import smutkiewicz.dafttapchallenge.entity.Score

interface Scores {

    fun add(score: Score): Boolean

    fun getTopFiveScores(): List<Score>
}