package es.sdos.android.project.common.extension

import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity


//region Visibility utils
fun View?.setVisible(condition: Boolean, doWhenVisible: () -> Unit = {}) {
    this?.visibility = if (condition) {
        doWhenVisible()
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View?.setInvisible(condition: Boolean) {
    this?.visibility = if (condition) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.isVisible(): Boolean {
    return this?.visibility == View.VISIBLE
}
//endregion

fun View.getLayoutInflater(): LayoutInflater = LayoutInflater.from(this.context)

fun View.getActivity(): FragmentActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is FragmentActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun View?.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    val layoutParams = this?.layoutParams
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        layoutParams.setMargins(left, top, right, bottom)
        this?.layoutParams = layoutParams
    }
}
