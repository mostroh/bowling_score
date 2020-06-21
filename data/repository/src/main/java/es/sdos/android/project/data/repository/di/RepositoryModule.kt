package es.sdos.android.project.data.repository.di

import dagger.Module
import dagger.Provides
import es.sdos.android.project.data.datasource.games.GamesLocalDataSource
import es.sdos.android.project.data.datasource.games.GamesRemoteDataSource
import es.sdos.android.project.data.repository.games.GamesRepository
import es.sdos.android.project.data.repository.games.GamesRepositoryImpl
import es.sdos.android.project.data.repository.util.AppDispatchers
import kotlinx.coroutines.Dispatchers

@Module
class RepositoryModule {

    @Provides
    fun appDispatchersProvider() =
        AppDispatchers(Dispatchers.Main, Dispatchers.IO)

    @Provides
    fun gamesRepositoryProvider(remote: GamesRemoteDataSource, local: GamesLocalDataSource) =
        GamesRepositoryImpl(remote, local) as GamesRepository

}
