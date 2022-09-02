package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.PhotoEntity
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.data.pagination.PhotoPagingDataSource
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetTopicPhotos @Inject constructor(private val photoRepository: PhotoRepository) : PagingUseCase<Photo, GetTopicPhotos.Params>() {
  class Params(val topicId: String)

  override fun buildFlow(params: Params): Flow<PagingData<Photo>> {
    return Pager(
      config = PagingConfig(20, 5),
      pagingSourceFactory = { PhotoPagingDataSource(photoRepository, params.topicId) }
    ).flow.map {
      it.map {
        Photo.fromEntity(it)
      }
    }
  }
}

