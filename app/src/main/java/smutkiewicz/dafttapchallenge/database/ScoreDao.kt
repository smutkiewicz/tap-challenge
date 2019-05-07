package smutkiewicz.dafttapchallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert
    fun add(dbScore: DbScore)

    @Query("SELECT * FROM dbscore ORDER BY amountOfTaps DESC, id ASC")
    fun get(): List<DbScore>

    @Query("SELECT * FROM dbscore WHERE amountOfTaps >= :amountOfTaps")
    fun getScoresHigherThan(amountOfTaps: Int): List<DbScore>
}