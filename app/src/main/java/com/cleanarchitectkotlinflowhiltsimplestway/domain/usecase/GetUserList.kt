package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.User
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.UserRepository
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetUserList @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<List<User>, Unit>() {

    override  fun invoke(param: Unit): Flow<State<List<User>>> {
        val firstUserCollectionFlow = flow {
            emit(userRepository.getUser())
        }
        val secondUserCollectionFlow = flow {
            emit(userRepository.getUser())
        }
        return firstUserCollectionFlow.zip(secondUserCollectionFlow) {
                users1, users2 ->
            val all = mutableListOf<User>()
            all.addAll(users1)
            all.addAll(users2)
            all.toList()
        }.flatMapMerge {
            flow<State<List<User>>> {
                delay(2000)
                emit(State.DataState(it))
            }
        }.onStart {
            emit(State.LoadingState)
        }.catch {
                cause: Throwable ->
            emit(State.ErrorState(cause))
        }.flowOn(Dispatchers.IO)
    }

}

