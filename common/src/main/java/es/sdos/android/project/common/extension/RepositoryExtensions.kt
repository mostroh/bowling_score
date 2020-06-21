package es.sdos.android.project.common.extension

import android.content.Context
import es.sdos.android.project.common.R
import es.sdos.android.project.data.model.error.AsyncError
import es.sdos.android.project.data.model.error.AsyncError.*


//region AsyncError utils
fun Context.getErrorMessage(error: AsyncError) =
    when (error) {
        is ConnectionError,
        is InvalidTokenError,
        is DataParseError,
        is ServerError -> this.getString(R.string.error__connection)

        else -> this.getString(R.string.error__unknown)
    }
