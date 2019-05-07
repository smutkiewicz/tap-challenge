package smutkiewicz.dafttapchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DbScore::class], version = 1, exportSchema = false)
abstract class ScoresDatabase: RoomDatabase() {
    abstract fun scoresDao(): ScoreDao

    companion object {
        lateinit var INSTANCE: ScoresDatabase
        private const val dbName = "scores_db"

        fun initIfNeeded(context: Context) {
            if (ScoresDatabase.Companion::INSTANCE.isInitialized.not()) {
                INSTANCE = Room
                    .databaseBuilder(context, ScoresDatabase::class.java, dbName)
                    .build()
            }
        }
    }
}