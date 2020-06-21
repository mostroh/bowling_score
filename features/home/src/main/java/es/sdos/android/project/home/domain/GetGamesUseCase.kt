package es.sdos.android.project.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.GameFilter
import es.sdos.android.project.data.repository.games.GamesRepository
import es.sdos.android.project.data.repository.util.AsyncResult
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetGamesUseCase @Inject constructor(private val repository: GamesRepository) {

    suspend operator fun invoke(filter: GameFilter? = null): LiveData<AsyncResult<List<GameBo>>> {
        return repository.getGames(filter).flow().asLiveData(coroutineContext)
    }

}
