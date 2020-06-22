package es.sdos.android.project.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.common.util.lifecycle.MutableSourceLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.GameFilter
import es.sdos.android.project.data.repository.util.AppDispatchers
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.home.domain.CreateGameUseCase
import es.sdos.android.project.home.domain.DeleteGameUseCase
import es.sdos.android.project.home.domain.GetGamesUseCase
import es.sdos.android.project.home.ui.fragment.HomeFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val createGameUseCase: CreateGameUseCase,
    private val getGamesUseCase: GetGamesUseCase,
    private val deleteGameUseCase: DeleteGameUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val pendingGameLiveData = MutableSourceLiveData<AsyncResult<List<GameBo>>>()

    private val _hasPendingGame = Transformations.map(pendingGameLiveData.liveData()) {pendingGameLiveData.liveData().value != null}
    val hasPendingGame : LiveData<Boolean> = _hasPendingGame

    init {
        requestPendingGame()
    }

    private fun requestPendingGame() {
        viewModelScope.launch(dispatchers.io) {
            pendingGameLiveData.changeSource(getGamesUseCase(GameFilter.NOT_FINISHED))
        }
    }

    fun createGame() : LiveData<AsyncResult<GameBo>> {
        val createGameLiveData = MutableSourceLiveData<AsyncResult<GameBo>>()
        viewModelScope.launch(dispatchers.io) {
            createGameLiveData.changeSource(createGameUseCase())
        }
        return createGameLiveData.liveData()
    }

    fun deleteGame(gameId: Long){
        viewModelScope.launch(dispatchers.io) {
            deleteGameUseCase.invoke(gameId)
        }

    }

    fun goToScores() {
        navigate(HomeFragmentDirections.goToScoreHistory())
    }

    fun goToGame(gameId: Long) {
        navigate(HomeFragmentDirections.goToGame(gameId))
    }


    fun getPendingGameLiveData() = pendingGameLiveData.liveData()
}