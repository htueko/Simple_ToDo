package com.htueko.simpletodo.common.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.htueko.simpletodo.common.data.remote.RemoteConstant
import com.htueko.simpletodo.common.domain.datasource.RemoteDataSource
import com.htueko.simpletodo.common.domain.executor.AppExecutor
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.util.State
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImpl @Inject constructor(
    private val taskExecutor: AppExecutor,
    private val instance: FirebaseFirestore
) : RemoteDataSource {

    private val backGroundTask = taskExecutor.io
    private val collection = RemoteConstant.TODO_COLLECTION
    private val firestore = instance.collection(collection)

    override fun getTodo(): Flow<State<List<Todo>>> =
        flow<State<List<Todo>>> {
            val snapShot = firestore.get().await()
            val data = snapShot.toObjects(Todo::class.java)
            emit(State.success(data))
        }.flowOn(backGroundTask)
            .catch { emit(State.error(it.localizedMessage)) }

    override fun getTodoById(id: Long): Flow<State<Todo>> =
        flow<State<Todo>> {
            val snapShot = firestore.document(id.toString()).get().await()
            val data = snapShot.toObject(Todo::class.java)
            if (data != null){
                emit(State.success(data))
            }
        }.flowOn(backGroundTask)
            .catch { emit(State.error(it.localizedMessage)) }

    override fun addToDo(todo: Todo): Flow<State<Boolean>> =
        flow<State<Boolean>> {
            val data = firestore.add(todo).await()
            emit(State.success(true))
        }.flowOn(backGroundTask)
            .catch { emit(State.error(it.localizedMessage)) }


    override fun removeToDo(todo: Todo): Flow<State<Boolean>> =
        flow<State<Boolean>> {
            firestore.document(todo.id.toString()).delete().await()
            emit(State.success(true))
        }.flowOn(backGroundTask)
            .catch { emit(State.error(it.localizedMessage)) }


    override fun updateToDo(todo: Todo): Flow<State<Boolean>> =
        flow<State<Boolean>> {
            val updateData = hashMapOf<Any, Any>(
                todo.title to todo.title,
                "desc" to todo.desc!!,
                todo.hasComplete to todo.hasComplete,
                todo.isImportant to todo.isImportant,
                todo.isUrgent to todo.isUrgent,
                todo.createAt to todo.createAt,
                todo.updateAt to todo.updateAt,
            )
            firestore.document(todo.id.toString())
                .set(updateData)
                .await()
            emit(State.success(true))
        }.flowOn(backGroundTask)
            .catch { emit(State.error(it.localizedMessage)) }


}