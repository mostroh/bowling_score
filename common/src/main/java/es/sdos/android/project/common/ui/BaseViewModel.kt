package es.sdos.android.project.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import es.sdos.android.project.common.util.lifecycle.Event
import es.sdos.android.project.navigation.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    // FOR NAVIGATION
    private val navigation = MutableLiveData<Event<NavigationCommand>>()

    fun getNavigation() = navigation as LiveData<Event<NavigationCommand>>

    // FOR ERROR HANDLER
    private val snackbarError = MutableLiveData<Event<String>>()

    fun getSnackbarError() = snackbarError as LiveData<Event<String>>

    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections) {
        navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigation.value = Event(NavigationCommand.Back)
    }
}
