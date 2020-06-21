package es.sdos.android.project.data.remote.games

import es.sdos.android.project.data.remote.games.dto.SynchronizeGamesResponseDto
import retrofit2.http.GET

interface GamesWs {
    @GET("SDOSLabs/AndroidTestJson/master/db.json")
    suspend fun getGames(): SynchronizeGamesResponseDto

}