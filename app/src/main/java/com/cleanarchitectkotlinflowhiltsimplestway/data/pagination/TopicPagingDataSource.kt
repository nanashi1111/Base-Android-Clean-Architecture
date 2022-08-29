package com.cleanarchitectkotlinflowhiltsimplestway.data.pagination

import androidx.paging.PagingSource
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import javax.inject.Inject

class TopicPagingDataSource @Inject constructor(private val photoRepository: PhotoRepository) : PagingSource<Int, Topic>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Topic> {
    try {
      val page = params.key ?: 1
      val data = photoRepository.getTopics(page)
      val nextPage = if (data.isNotEmpty()) {
        page + 1
      } else {
        null
      }
      return LoadResult.Page(
        data = data.map {
          Topic.fromEntity(it)
        },
        nextKey = nextPage,
        prevKey = null
      )
    } catch (e: Exception) {
      return LoadResult.Error(e)
    }
  }
}