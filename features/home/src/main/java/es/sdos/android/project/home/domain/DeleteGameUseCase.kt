package es.sdos.android.project.home.domain

import es.sdos.android.project.data.repository.games.GamesRepository
import javax.inject.Inject

class DeleteGameUseCase @Inject constructor(private val repository: GamesRepository) {

    suspend operator fun invoke(gameId: Long) {
        return repository.deleteGame(gameId)
    }

}