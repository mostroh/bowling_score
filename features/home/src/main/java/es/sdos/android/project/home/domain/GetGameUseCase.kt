package es.sdos.android.project.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.games.GamesRepository
import es.sdos.android.project.data.repository.util.AsyncResult
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetGameUseCase @Inject constructor(private val repository: GamesRepository) {

    suspend operator fun invoke(gameId: Long): LiveData<AsyncResult<GameBo?>> {
        return repository.getGame(gameId).flow().asLiveData(coroutineContext)
    }

}
