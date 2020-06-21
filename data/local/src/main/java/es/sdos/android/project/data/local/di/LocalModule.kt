package es.sdos.android.project.data.local.di

import android.content.Context
import dagger.Module
import dagger.Provides
import es.sdos.android.project.data.datasource.games.GamesLocalDataSource
import es.sdos.android.project.data.local.AppRoomDatabase
import es.sdos.android.project.data.local.games.*
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun appRoomDatabaseProvider(context: Context) =
        AppRoomDatabase.buildDatabase(context)

    @Provides
    fun gamesDaoProvider(database: AppRoomDatabase) =
        database.gamesDao()

    @Provides
    fun newsLocalDataSourceProvider(gamesDao: GamesDao) =
        GamesLocalDataSourceImpl(gamesDao) as GamesLocalDataSource

}