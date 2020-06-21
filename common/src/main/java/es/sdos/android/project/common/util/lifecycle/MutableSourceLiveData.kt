package es.sdos.android.project.common.util.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Jose Fuentes
 *
 * Clase de utilidad para facilitar la lógica a desarrollar cuando para cada refresco de una fuente de datos se genera un nuevo
 * LiveData en repositorio. Lo que conseguimos con esta clase es que la vista observe un único LiveData y se vaya cambiando la
 * fuente cada vez que hay una nueva petición (por ejemplo que el usuario fuerce el refresco de datos o cuando existe paginación
 * y se añade una nueva página a los datos existentes).
 *
 */
open class MutableSourceLiveData<Type> : Observer<Type> {

    //region Fields
    private val mediatorLiveData = MediatorLiveData<Type>()
    protected var actualSource: LiveData<Type> = MutableLiveData<Type>()
    //endregion

    init {
        mediatorLiveData.addSource(actualSource, this)
    }

    //region Public methods
    fun changeSource(source: LiveData<Type>) {
        GlobalScope.launch(Dispatchers.Main) {
            removeSource(actualSource)
            addSource(source)
            actualSource = source
        }
    }

    fun liveData() = mediatorLiveData as LiveData<Type>
    //endregion

    //region Protected methods
    protected open fun removeSource(sourceToRemove: LiveData<Type>) {
        mediatorLiveData.removeSource(sourceToRemove)
    }

    protected open fun addSource(sourceToAdd: LiveData<Type>) {
        mediatorLiveData.addSource(sourceToAdd, this)
    }
    //endregion

    //region override Observer<Type>
    override fun onChanged(value: Type?) {
        mediatorLiveData.postValue(value)
    }
    //endregion
}
