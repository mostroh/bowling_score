package es.sdos.android.project.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.sdos.android.project.data.local.converter.DateConverter
import es.sdos.android.project.data.local.games.GamesDao
import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.local.games.dbo.RoundDbo

@Database(entities = [GameDbo::class, RoundDbo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object {
        private const val DATABASE_NAME = "RoomDatabase.db"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
