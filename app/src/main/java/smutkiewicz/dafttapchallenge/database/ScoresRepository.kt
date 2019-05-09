package smutkiewicz.dafttapchallenge.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import smutkiewicz.dafttapchallenge.entity.Score

class ScoresRepository(private val scoreDao: ScoreDao): Scores {

    override suspend fun add(score: Score): Boolean = withContext(Dispatchers.Default) {
        val scoresEqualOrHigherThanMine = scoreDao.getScoresEqualOrHigherThan(score.amountOfTaps)

        if (checkIfMyScoreQualifiesToHighScores(scoresEqualOrHigherThanMine, score.amountOfTaps)) {
            scoreDao.add(DbScore.fromEntity(score))
            return@withContext true
        }

        false
    }

    override suspend fun getTopFiveScores(): List<Score> = withContext(Dispatchers.Default) {
        val list = scoreDao.get()
        val amountOfResults = if (list.size > MAX_RESULTS) MAX_RESULTS else list.size
        var position = 1

        list
            .takeIf { list.isNotEmpty() }
            ?.subList(0, amountOfResults)
            ?.map { it.toEntity(position++) }
            ?.toList()
            ?: emptyList()
    }

    private fun checkIfMyScoreQualifiesToHighScores(scoresEqualOrHigherThanMine: List<DbScore>, amountOfTaps: Int)
            = scoresEqualOrHigherThanMine.size < MAX_RESULTS
            && scoresEqualOrHigherThanMine.all { s -> s.amountOfTaps != amountOfTaps }

    private companion object {
        const val MAX_RESULTS = 5
    }
}