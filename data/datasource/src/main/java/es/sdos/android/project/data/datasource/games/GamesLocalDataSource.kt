package es.sdos.android.project.data.datasource.games

import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.GameFilter

interface GamesLocalDataSource {

    suspend fun getGame(gameId: Long): GameBo?

    suspend fun getGames(filter: GameFilter? = null): List<GameBo>

    suspend fun saveGames(games: List<GameBo>)

    suspend fun createGame(): GameBo

    suspend fun deleteGame(gameId: Long)

    suspend fun addShot(gameId: Long, shotScore: Int): GameBo?

}