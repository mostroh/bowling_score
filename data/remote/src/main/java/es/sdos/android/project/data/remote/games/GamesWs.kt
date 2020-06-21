package es.sdos.android.project.data.remote.games

import es.sdos.android.project.data.remote.games.dto.SynchronizeGamesResponseDto

interface GamesWs {

    suspend fun getGames(): SynchronizeGamesResponseDto

}