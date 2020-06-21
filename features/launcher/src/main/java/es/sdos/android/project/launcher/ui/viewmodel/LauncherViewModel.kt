package es.sdos.android.project.launcher.ui.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.viewModelScope
import es.sdos.android.project.common.ui.BaseViewModel
import es.sdos.android.project.common.util.lifecycle.MutableSourceLiveData
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.repository.util.AppDispatchers
import es.sdos.android.project.data.repository.util.AsyncResult
import es.sdos.android.project.launcher.domain.SynchronizeGamesUseCase
import es.sdos.android.project.launcher.ui.fragment.LauncherFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

class LauncherViewModel @Inject constructor(
    private val synchronizeGamesUseCase: SynchronizeGamesUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val synchronizationStatus = MutableSourceLiveData<AsyncResult<List<GameBo>>>()

    init {
        viewModelScope.launch(dispatchers.io) {
            synchronizationStatus.changeSource(synchronizeGamesUseCase.invoke())
        }
    }

    fun getSynchronizationStatus() = synchronizationStatus.liveData()

    fun synchronizationFinish() {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // No-op
            }

            override fun onFinish() {
                navigate(LauncherFragmentDirections.goToHome())
            }
        }.start()
    }

}