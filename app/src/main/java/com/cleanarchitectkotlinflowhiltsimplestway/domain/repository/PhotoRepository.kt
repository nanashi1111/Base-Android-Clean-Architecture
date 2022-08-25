package com.cleanarchitectkotlinflowhiltsimplestway.domain.repository

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.TopicEntity

interface PhotoRepository {
  suspend fun getTopics(page: Int): List<TopicEntity>
}