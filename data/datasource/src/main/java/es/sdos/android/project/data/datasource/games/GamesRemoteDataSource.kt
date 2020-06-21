package es.sdos.android.project.data.datasource.games

import es.sdos.android.project.data.model.game.GameBo

interface GamesRemoteDataSource {

    suspend fun getGames(): List<GameBo>

}