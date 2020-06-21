package es.sdos.android.project.data.repository.util

import es.sdos.android.project.data.model.error.AsyncError


data class AsyncResult<out T>(val status: Status, val data: T?, val error: AsyncError?) {
    companion object {
        fun <T> success(data: T?): AsyncResult<T> {
            return AsyncResult(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: AsyncError, data: T?): AsyncResult<T> {
            return AsyncResult(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): AsyncResult<T> {
            return AsyncResult(
                Status.LOADING,
                data,
                null
            )
        }

        fun <NEW> changeData(original: AsyncResult<*>, data: NEW?): AsyncResult<NEW> {
            return AsyncResult<NEW>(original.status, data, original.error)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
