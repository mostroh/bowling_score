package es.sdos.android.project.data.repository.util

import es.sdos.android.project.data.model.error.AsyncError
import es.sdos.android.project.data.model.error.KoreException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.util.logging.Level
import java.util.logging.Logger

abstract class CacheableRemoteResponse<ResultType> {

    private val log = Logger.getLogger(CacheableRemoteResponse::class.java.name)

    private lateinit var flow: Flow<AsyncResult<ResultType>>
    private lateinit var deferredValue: Deferred<AsyncResult<ResultType>>

    //region RepositoryResponse
    private val response = object : RepositoryResponse<ResultType> {
        override fun flow() = flow
        override fun valueAsync() = deferredValue
    }
    //endregion

    //region Logic Template
    suspend fun build(): RepositoryResponse<ResultType> {
        flow = channelFlow {
            coroutineScope {
                deferredValue = async {
                    send(AsyncResult.loading(null))
                    val dbResult = loadFromLocal()
                    val finalValue: AsyncResult<ResultType>
                    finalValue = if (dbResult == null || shouldRequestFromRemote(dbResult)) {
                        try {
                            log.info("Fetch data from network")
                            send(AsyncResult.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
                            val networkResponse = fetchFromNetwork()
                            AsyncResult.success(networkResponse)
                        } catch (e: Exception) {
                            log.log(Level.WARNING, "An error happened: ", e)
                            val asyncError = (e as? KoreException)?.asyncError ?: AsyncError.UnknownError("An error happened", e)
                            AsyncResult.error(asyncError, dbResult)
                        }
                    } else {
                        log.info("Return data from local database")
                        AsyncResult.success(dbResult)
                    }
                    send(finalValue)
                    finalValue
                }
            }
        }
        return response
    }

    private suspend fun fetchFromNetwork(): ResultType {
        val networkResponse = requestRemoteCall()
        log.info("Data fetched from network")
        saveRemoteResponse(networkResponse)
        return networkResponse
    }
    //endregion

    //region Abstract methods
    protected abstract suspend fun loadFromLocal(): ResultType?

    protected abstract fun shouldRequestFromRemote(localResponse: ResultType): Boolean

    protected abstract suspend fun requestRemoteCall(): ResultType

    protected abstract suspend fun saveRemoteResponse(remoteResponse: ResultType)
    //endregion
}
