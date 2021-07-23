package com.htueko.simpletodo.common.domain.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.htueko.simpletodo.common.util.State
import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class NetworkBoundResource<Local, Remote> {

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(item: Remote)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract suspend fun fetchFromLocal(): Flow<Local>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(name: String): Response<Remote>

    fun asFlow(name: String) = flow<State<Local>> {
        // Emit Loading State
        emit(State.loading())
        // Emit Database content first
        emit(State.success(fetchFromLocal().first()))
        // // Fetch latest data from remote
        val apiResponse = fetchFromRemote(name)
        // Check for response validation
        if (apiResponse.isSuccessful && apiResponse.body() != null) {
            // Save data into the persistence storage
            saveRemoteData(apiResponse.body()!!)
        } else {
            // Something went wrong! Emit Error state.
            emit(State.error(apiResponse.message()))
        }
        // Retrieve posts from persistence storage and emit
        emitAll(
            fetchFromLocal().map {
                State.success<Local>(it)
            }
        )

    }.catch { error ->
        // Exception occurred! Emit error
        emit(State.error("Network error!"))
        error.printStackTrace()
    }

}