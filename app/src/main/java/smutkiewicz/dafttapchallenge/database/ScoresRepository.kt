package smutkiewicz.dafttapchallenge.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import smutkiewicz.dafttapchallenge.entity.Score

class ScoresRepository(private val scoreDao: ScoreDao): Scores {

    override suspend fun add(score: Score): Boolean = withContext(Dispatchers.Default) {
        val scoresHigherThanMine = scoreDao.getScoresHigherThan(score.amountOfTaps)

        if (scoresHigherThanMine.size < MAX_RESULTS) {
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

    private companion object {
        const val MAX_RESULTS = 5
    }
}