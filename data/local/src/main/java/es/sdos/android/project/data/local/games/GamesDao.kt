package es.sdos.android.project.data.local.games

import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.local.games.dbo.RoundDbo

abstract class GamesDao {

    abstract suspend fun getGame(gameId: Long): GameDbo?

    abstract suspend fun getGames(): List<GameDbo>

    abstract suspend fun getRounds(gameId: Long): List<RoundDbo>

    abstract suspend fun saveGame(gameDbo: GameDbo) : Long

    abstract suspend fun saveRound(roundDbo: RoundDbo) : Long

    abstract suspend fun deleteGame(gameId: Long)

    abstract suspend fun deleteRounds(gameId: Long)

}