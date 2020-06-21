package es.sdos.android.project.common.extension

import android.app.Activity

/**
 * Return the instance if activity is active or return null in otherwise
 */
fun Activity?.getIfActive() =
    if (this.canUse()) {
        this
    } else {
        null
    }

/**
 * Return true if activity is not null and not finishing
 */
fun Activity?.canUse() = this?.isFinishing == false
