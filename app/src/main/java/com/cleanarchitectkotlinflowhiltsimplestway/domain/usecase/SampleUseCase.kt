package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.User
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.UserRepository
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.UseCase
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<List<User>, Unit>() {

    override suspend fun invoke(param: Unit): Flow<State<List<User>>> {
        return flow {
            emit(State.LoadingState)
            try {
                emit(State.DataState(userRepository.getUser()))
            } catch (e: Exception) {
                emit(State.ErrorState(e))
            }
        }
    }

}

