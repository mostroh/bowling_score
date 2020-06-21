package es.sdos.android.project.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.common.util.lifecycle.MutableSourceLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AppDispatchers
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.home.domain.GetGamesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScoreHistoryViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val finishedGamesLiveData = MutableSourceLiveData<AsyncResult<List<GameBo>>>()

    init {
        requestGames()
    }

    private fun requestGames() = viewModelScope.launch(dispatchers.io) {
        finishedGamesLiveData.changeSource(getGamesUseCase())
    }

    fun getFinishedGamesLiveData() = finishedGamesLiveData.liveData()

}