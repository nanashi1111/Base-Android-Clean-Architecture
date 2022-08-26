package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopics @Inject constructor(private val photoRepository: PhotoRepository) : UseCase<List<Topic>, Int>() {
  override fun buildFlow(param: Int): Flow<State<List<Topic>>> {
    return flow<State<List<Topic>>> {
      val data = photoRepository.getTopics(param).map {
        Topic.fromEntity(it)
      }
      emit(State.DataState(data))
    }
  }
}