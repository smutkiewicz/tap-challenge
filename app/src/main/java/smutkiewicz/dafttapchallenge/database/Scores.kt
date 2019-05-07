package smutkiewicz.dafttapchallenge.database

import smutkiewicz.dafttapchallenge.entity.Score

interface Scores {

    suspend fun add(score: Score): Boolean

    suspend fun getTopFiveScores(): List<Score>
}