package es.sdos.android.project.data.remote.games

import es.sdos.android.project.data.datasource.games.GamesRemoteDataSource
import es.sdos.android.project.data.remote.error.RemoteErrorManagement

class GamesRemoteDataSourceImpl(
    private val gamesWs: GamesWs
) : GamesRemoteDataSource {

    override suspend fun getGames() = RemoteErrorManagement.wrap {
        gamesWs.getGames().game_scores.map { it.toBo() }
    }

}