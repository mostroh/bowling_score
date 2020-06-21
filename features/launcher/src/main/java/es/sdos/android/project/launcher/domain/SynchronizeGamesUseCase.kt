package es.sdos.android.project.launcher.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.games.GamesRepository
import es.sdos.android.project.data.repository.util.AsyncResult
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class SynchronizeGamesUseCase @Inject constructor(private val repository: GamesRepository) {

    suspend operator fun invoke(): LiveData<AsyncResult<List<GameBo>>> {
        return repository.synchronizeData().flow().asLiveData(coroutineContext)
    }
}