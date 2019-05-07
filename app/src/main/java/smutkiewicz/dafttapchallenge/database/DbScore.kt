package smutkiewicz.dafttapchallenge.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import smutkiewicz.dafttapchallenge.entity.Score

@Entity
data class DbScore(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amountOfTaps: Int,
    val timestamp: String)
{
    fun toEntity(position: Int) = Score(position, amountOfTaps, timestamp)

    companion object {
        fun fromEntity(entity: Score) = DbScore(0, entity.amountOfTaps, entity.timestamp)
    }
}