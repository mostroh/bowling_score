package es.sdos.android.project.data.local.games

import es.sdos.android.project.data.datasource.games.GamesLocalDataSource
import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.GameFilter
import es.sdos.android.project.data.model.game.addShot
import java.util.Date

class GamesLocalDataSourceImpl(
    private val gamesDao: GamesDao
) : GamesLocalDataSource {

    override suspend fun getGame(gameId: Long): GameBo? {
        return gamesDao.getGame(gameId)?.toBo()?.copy(rounds = gamesDao.getRounds(gameId).map { it.toBo() })
    }

    override suspend fun getGames(filter: GameFilter?): List<GameBo> {
        TODO()
    }

    override suspend fun saveGames(games: List<GameBo>) {
        games.forEach { game ->
            TODO()
        }
    }

    override suspend fun createGame(): GameBo {
        TODO()
    }

    override suspend fun deleteGame(gameId: Long) {
        gamesDao.deleteGame(gameId)
        gamesDao.deleteRounds(gameId)
    }

    override suspend fun addShot(gameId: Long, shotScore: Int): GameBo? {
        val newRounds = gamesDao.getRounds(gameId).map { it.toBo() }.addShot(gameId, shotScore)
        newRounds.forEach {
            gamesDao.saveRound(it.toDbo())
        }

        val game = gamesDao.getGame(gameId)
        if (game != null) {
            //TODO
        }

        return getGame(gameId)
    }
}
