package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopicPhotos @Inject constructor(private val photoRepository: PhotoRepository) : UseCase<List<Photo>, GetTopicPhotos.Params>() {

  class Params(val topicId: String, val page: Int)

  override fun buildFlow(param: Params): Flow<State<List<Photo>>> {
    return flow<State<List<Photo>>> {
      val data = photoRepository.getTopicDetail(param.topicId, param.page).map { Photo.fromEntity(it) }
      emit(State.DataState(data))
    }
  }
}