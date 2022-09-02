package com.cleanarchitectkotlinflowhiltsimplestway.data.pagination

import androidx.paging.PagingSource
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.PhotoEntity
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository

class PhotoPagingDataSource constructor(private val photoRepository: PhotoRepository, private val topicId: String) : PagingSource<Int, PhotoEntity>() {


  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoEntity> {
    return try {
      val page = params.key ?: 1
      val data = photoRepository.getTopicDetail(topicId, page)
      LoadResult.Page(
        data = data,
        prevKey = null,
        nextKey = if (data.isNotEmpty()) {
          1 + page
        } else {
          null
        }
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
}