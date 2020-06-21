package es.sdos.android.project.common.util.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import es.sdos.android.project.data.repository.util.AsyncResult

class MutableAsyncSourceLiveData<T> : MutableSourceLiveData<AsyncResult<T>>() {

    //region Fields
    private val mediatorValueLiveData = MediatorLiveData<T>()
    //endregion

    init {
        mediatorValueLiveData.addSource(actualSource, this)
    }

    //region Public methods
    fun valueLiveData() = mediatorValueLiveData as LiveData<T>
    //endregion

    //region Protected methods
    override fun removeSource(sourceToRemove: LiveData<AsyncResult<T>>) {
        super.removeSource(sourceToRemove)
        mediatorValueLiveData.removeSource(sourceToRemove)
    }

    override fun addSource(sourceToAdd: LiveData<AsyncResult<T>>) {
        super.addSource(sourceToAdd)
        mediatorValueLiveData.addSource(sourceToAdd, this)
    }
    //endregion

    //region override Observer<AsyncResult<T>>
    override fun onChanged(value: AsyncResult<T>?) {
        super.onChanged(value)
        if (value?.status == AsyncResult.Status.SUCCESS) {
            mediatorValueLiveData.postValue(value.data)
        }
    }
    //endregion
}
