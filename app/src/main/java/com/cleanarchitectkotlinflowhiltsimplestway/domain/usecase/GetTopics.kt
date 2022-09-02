package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import androidx.paging.*
import com.cleanarchitectkotlinflowhiltsimplestway.data.pagination.TopicPagingDataSource
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTopics @Inject constructor(private val photoRepository: PhotoRepository) : PagingUseCase<Topic, Unit>() {

  override  fun buildFlow(params: Unit): Flow<PagingData<Topic>> {
    return Pager(
      config = PagingConfig(10,5),
      pagingSourceFactory = { TopicPagingDataSource(photoRepository) }
    ).flow.map { pagingData ->
      pagingData.map {
        entity ->
        Topic.fromEntity(entity)
      }
    }

  }
}


