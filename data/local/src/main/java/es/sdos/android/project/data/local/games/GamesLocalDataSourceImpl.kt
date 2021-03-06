package es.sdos.android.project.data.local.games

import es.sdos.android.project.data.datasource.games.GamesLocalDataSource
import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.GameFilter
import es.sdos.android.project.data.model.game.addShot
import es.sdos.android.project.data.model.game.isComplete
import java.util.Calendar
import java.util.Date
import kotlin.math.round

class GamesLocalDataSourceImpl(
    private val gamesDao: GamesDao
) : GamesLocalDataSource {

    override suspend fun getGame(gameId: Long): GameBo? {
        return gamesDao.getGame(gameId)?.toBo()
    }

    override suspend fun getGames(filter: GameFilter?): List<GameBo> {
        val games = gamesDao.getGames().map { it.toBo() }
        return if (filter != null) {
            when (filter){
                GameFilter.FINISHED -> games.filter {it.finished}
                GameFilter.NOT_FINISHED -> games.filter {!it.finished}
            }
        } else {
            games
        }
    }

    override suspend fun saveGames(games: List<GameBo>) {
        games.forEach { game ->
            gamesDao.saveGame(game.toDbo())
            game.rounds.forEach{
                round ->
                if (round.id != null) {
                    gamesDao.updateRound(round.toDbo())
                }
                gamesDao.saveRound(round.toDbo())
            }
        }
    }

    override suspend fun createGame(): GameBo? {
        val newGame=  GameBo(null,
        Calendar.getInstance().time,
            emptyList(),
            0,
            false)
        val newId = gamesDao.saveGame(newGame.toDbo())
        return gamesDao.getGame(newId)?.toBo()
    }

    override suspend fun deleteGame(gameId: Long) {
        gamesDao.deleteGame(gameId)
        gamesDao.deleteRounds(gameId)
    }

    override suspend fun addShot(gameId: Long, shotScore: Int): GameBo? {
        val oldRounds = gamesDao.getRounds(gameId).map { it.toBo() }
        val newRounds = oldRounds.addShot(gameId, shotScore)
        newRounds.forEach {
            if (it.id!=null){
                gamesDao.updateRound(it.toDbo())
            } else {
                gamesDao.saveRound(it.toDbo())
            }
        }

        var game = gamesDao.getGame(gameId)
        if (game != null) {
            val newRoundsDbo = newRounds.map { it.toDbo() }
            game = game.copy(rounds = newRoundsDbo,
            totalScore = newRoundsDbo.last().score ?: 0,
            finished = newRounds.last().roundNum == 10 && newRounds.last().isComplete())
            gamesDao.updateGame(game)
        }

        return getGame(gameId)
    }
}
