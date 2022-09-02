package com.cleanarchitectkotlinflowhiltsimplestway.data.repository

import com.cleanarchitectkotlinflowhiltsimplestway.data.remote.Api
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val api: Api) : PhotoRepository {

  override suspend fun getTopics(page: Int) = api.getTopics(page)

  override suspend fun getTopicDetail(topicId: String, page: Int) = api.getTopicPhotos(topicId, page = page)
}