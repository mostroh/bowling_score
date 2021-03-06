package es.sdos.android.project.data.local.games

import androidx.room.*
import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.local.games.dbo.RoundDbo

@Dao
abstract class GamesDao {

    @Query("SELECT * FROM GameDbo WHERE id = :gameId")
    abstract suspend fun getGame(gameId: Long): GameDbo?

    @Query("SELECT * FROM GameDbo")
    abstract suspend fun getGames(): List<GameDbo>

    @Query("SELECT * FROM RoundDbo WHERE gameId = :gameId ORDER BY roundNum")
    abstract suspend fun getRounds(gameId: Long): List<RoundDbo>

    @Insert
    abstract suspend fun saveGame(gameDbo: GameDbo) : Long

    @Update
    abstract suspend fun updateGame(gameDbo: GameDbo)

    @Insert()
    abstract suspend fun saveRound(roundDbo: RoundDbo) : Long

    @Update()
    abstract suspend fun updateRound(roundDbo: RoundDbo)

    @Query("DELETE FROM GameDbo WHERE id = :gameId")
    abstract suspend fun deleteGame(gameId: Long)

    @Query("DELETE FROM RoundDbo WHERE gameId = :gameId")
    abstract suspend fun deleteRounds(gameId: Long)

}