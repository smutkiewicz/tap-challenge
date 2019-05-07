package smutkiewicz.dafttapchallenge.database

import smutkiewicz.dafttapchallenge.entity.Score

class ScoresRepository(private val scoreDao: ScoreDao): Scores {

    override fun add(score: Score): Boolean {
        val scoresHigherThanMine = scoreDao.getScoresHigherThan(score.amountOfTaps)

        if(scoresHigherThanMine.size < 5) {
            scoreDao.add(DbScore.fromEntity(score))
            return true
        }

        return false
    }

    override fun getTopFiveScores(): List<Score> {
        val list = scoreDao.get()
        val amountOfResults = if (list.size > 5) 5 else list.size
        var position = 1

        return if(list.isEmpty()) emptyList()
        else list.subList(0, amountOfResults).map { it.toEntity(position++) }.toList()
    }
}