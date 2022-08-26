package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.UserEntity
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.User
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetUserList @Inject constructor(
  private val userRepository: UserRepository
) : UseCase<List<User>, Unit>() {

  override fun buildFlow(param: Unit): Flow<State<List<User>>> {
    val firstUserCollectionFlow = flow {
      emit(userRepository.getUser())
    }
    val secondUserCollectionFlow = flow {
      emit(userRepository.getUser())
    }
    return firstUserCollectionFlow.zip(secondUserCollectionFlow) { users1, users2 ->
      val all = mutableListOf<UserEntity>()
      all.addAll(users1)
      all.addAll(users2)
      all.toList().map {
        User.fromEntity(it)
      }
    }.flatMapMerge {
      flow<State<List<User>>> {
        delay(2000)
        emit(State.DataState(it))
      }
    }
  }

}

