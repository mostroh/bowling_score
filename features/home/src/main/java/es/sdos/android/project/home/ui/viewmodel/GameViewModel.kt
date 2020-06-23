package es.sdos.android.project.home.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.common.util.lifecycle.MutableSourceLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AppDispatchers
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.home.domain.AddShotUseCase
import es.sdos.android.project.home.domain.GetGameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val addShotUseCase: AddShotUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val gameLiveData = MutableSourceLiveData<AsyncResult<GameBo?>>()

    val lastShot = MutableLiveData("")

    fun requestGame(gameId: Long) = viewModelScope.launch(dispatchers.io) {
        gameLiveData.changeSource(getGameUseCase(gameId))
    }

    fun addShot(gameId: Long) {
        viewModelScope.launch(dispatchers.io) {
            lastShot.value?.toInt().let {
                gameLiveData.changeSource(addShotUseCase(gameId, it!!))
                lastShot.postValue("")
            }
        }
    }

    fun getGameLiveData() = gameLiveData.liveData()

}