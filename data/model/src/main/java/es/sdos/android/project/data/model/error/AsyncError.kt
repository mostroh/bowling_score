package es.sdos.android.project.data.model.error

sealed class AsyncError(val debugMessage: String) {
    class ConnectionError(debugMessage: String) : AsyncError(debugMessage)
    class DataParseError(debugMessage: String) : AsyncError(debugMessage)
    class InvalidTokenError(debugMessage: String) : AsyncError(debugMessage)
    class ServerError(val code: Int, debugMessage: String) : AsyncError(debugMessage)
    class UnknownError(debugMessage: String, val errorThrowed: Throwable) : AsyncError(debugMessage)
    open class CustomError(debugMessage: String) : AsyncError(debugMessage)
}