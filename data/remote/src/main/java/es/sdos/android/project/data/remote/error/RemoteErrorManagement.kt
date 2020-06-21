package es.sdos.android.project.data.remote.error

import com.google.gson.JsonParseException
import es.sdos.android.project.data.model.error.AsyncError
import es.sdos.android.project.data.model.error.KoreException
import retrofit2.HttpException
import java.net.UnknownHostException
import java.util.logging.Level
import java.util.logging.Logger

object RemoteErrorManagement {

    private val log = Logger.getLogger(RemoteErrorManagement::class.java.name)

    /**
     * Calls the specified function [block] and returns its result. Catch any Exception and convert to a SdosException.
     */
    inline fun <T> wrap(block: () -> T): T {
        return try {
            block()
        } catch (e: Throwable) {
            throw KoreException(processError(e))
        }
    }

    fun processError(throwable: Throwable): AsyncError {
        log.log(Level.INFO, "RemoteErrorManagement", throwable)
        return when (throwable) {
            is HttpException -> processRetrofitError(throwable)
            is UnknownHostException -> AsyncError.ConnectionError(throwable.message ?: "Error de conexion")
            is JsonParseException -> AsyncError.DataParseError(throwable.message ?: "Error de parseo")
            else -> AsyncError.UnknownError(throwable.message ?: "Error desconocido", throwable)
        }
    }

    private fun processRetrofitError(httpException: HttpException): AsyncError {
        val errorCode = httpException.code()
        val url = httpException.response()?.raw()?.request?.url?.toString() ?: ""
        return AsyncError.ServerError(errorCode, url)
    }
}