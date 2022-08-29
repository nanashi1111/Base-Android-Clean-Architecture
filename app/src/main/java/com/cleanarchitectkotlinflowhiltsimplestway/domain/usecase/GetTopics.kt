package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import androidx.paging.*
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.data.pagination.TopicPagingDataSource
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopics @Inject constructor(private val photoRepository: PhotoRepository) : PagingUseCase<Topic>() {

  override  fun buildFlow(): Flow<PagingData<Topic>> {
    return Pager(
      config = PagingConfig(10,5),
      pagingSourceFactory = { TopicPagingDataSource(photoRepository) }
    ).flow

  }
}


