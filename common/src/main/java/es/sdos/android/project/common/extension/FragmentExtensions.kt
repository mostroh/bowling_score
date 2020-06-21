package es.sdos.android.project.common.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import es.sdos.android.project.common.util.lifecycle.Event

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun Fragment.showSnackbar(snackbarText: String, timeLength: Int = Snackbar.LENGTH_LONG) {
    activity.getIfActive()?.let { Snackbar.make(it.findViewById<View>(android.R.id.content), snackbarText, timeLength).show() }
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun Fragment.setupSnackbar(lifecycleOwner: LifecycleOwner, snackbarEvent: LiveData<Event<String>>, timeLength: Int = Snackbar.LENGTH_LONG) {
    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let { text ->
            context?.let { showSnackbar(text, timeLength) }
        }
    })
}

/**
 * Return the activity if is active or return null in otherwise
 */
fun Fragment.getActivityIfActive() = activity.getIfActive()
