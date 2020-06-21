package es.sdos.android.project.data.repository.games

import es.sdos.android.project.data.datasource.games.GamesLocalDataSource
import es.sdos.android.project.data.datasource.games.GamesRemoteDataSource
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.GameFilter
import es.sdos.android.project.data.repository.util.CacheableRemoteResponse
import es.sdos.android.project.data.repository.util.LocalResponse
import es.sdos.android.project.data.repository.util.RepositoryResponse

interface GamesRepository {

    suspend fun synchronizeData(): RepositoryResponse<List<GameBo>>

    suspend fun createGame(): RepositoryResponse<GameBo>

    suspend fun getGame(gameId: Long): RepositoryResponse<GameBo?>

    suspend fun getGames(filter: GameFilter? = null): RepositoryResponse<List<GameBo>>

    suspend fun addShot(gameId: Long, shotScore: Int): RepositoryResponse<GameBo?>

}

internal class GamesRepositoryImpl(
    private val remote: GamesRemoteDataSource,
    private val local: GamesLocalDataSource
) : GamesRepository {

    override suspend fun synchronizeData(): RepositoryResponse<List<GameBo>> {
        return object : CacheableRemoteResponse<List<GameBo>>() {
            override suspend fun loadFromLocal(): List<GameBo>? {
                return null
            }

            override fun shouldRequestFromRemote(localResponse: List<GameBo>): Boolean {
                return true
            }

            override suspend fun requestRemoteCall(): List<GameBo> {
                return remote.getGames()
            }

            override suspend fun saveRemoteResponse(remoteResponse: List<GameBo>) {
                local.saveGames(remoteResponse)
            }

        }.build()
    }

    override suspend fun createGame(): RepositoryResponse<GameBo> {
        return object : LocalResponse<GameBo>() {
            override suspend fun loadFromLocal(): GameBo? {
                return local.createGame()
            }
        }.build()
    }

    override suspend fun getGame(gameId: Long): RepositoryResponse<GameBo?> {
        return object : LocalResponse<GameBo?>() {
            override suspend fun loadFromLocal(): GameBo? {
                return local.getGame(gameId)
            }
        }.build()
    }

    override suspend fun getGames(filter: GameFilter?): RepositoryResponse<List<GameBo>> {
        return object : LocalResponse<List<GameBo>>() {
            override suspend fun loadFromLocal(): List<GameBo>? {
                return local.getGames(filter)
            }
        }.build()
    }

    override suspend fun addShot(gameId: Long, shotScore: Int): RepositoryResponse<GameBo?> {
        return object : LocalResponse<GameBo?>() {
            override suspend fun loadFromLocal(): GameBo? {
                return local.addShot(gameId, shotScore)
            }
        }.build()
    }

}
