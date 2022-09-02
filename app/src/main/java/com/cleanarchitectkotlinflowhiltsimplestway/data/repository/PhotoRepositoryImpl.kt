package com.cleanarchitectkotlinflowhiltsimplestway.data.repository

import android.content.Context
import com.cleanarchitectkotlinflowhiltsimplestway.data.remote.Api
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.FileUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.ResponseBody
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val api: Api, private val context: Context) : PhotoRepository {

  override suspend fun getTopics(page: Int) = api.getTopics(page)

  override suspend fun getTopicDetail(topicId: String, page: Int) = api.getTopicPhotos(topicId, page = page)

  override suspend fun downloadPhoto(url: String, fileName: String): String {
    val body = api.downloadPhoto(url)
    return  FileUtils.saveFile(context, body, fileName)
  }
}