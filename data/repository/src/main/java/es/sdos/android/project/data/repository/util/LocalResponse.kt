package es.sdos.android.project.data.repository.util

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.util.logging.Logger

abstract class LocalResponse<ResultType> {

    private val log = Logger.getLogger(LocalResponse::class.java.name)

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
                    log.info("Return data from local database")
                    val value = AsyncResult.success(dbResult)
                    send(value)
                    value
                }
            }
        }
        return response
    }
    //endregion

    //region Abstract methods
    protected abstract suspend fun loadFromLocal(): ResultType?
    //endregion
}
