package com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DownloadPhoto @Inject constructor(private val repository: PhotoRepository): UseCase<String, DownloadPhoto.Params>() {

  class Params(val url: String, val fileName: String)

  override fun buildFlow(param: Params): Flow<State<String>> {
    return flow {
      emit(State.DataState(repository.downloadPhoto(param.url, param.fileName)))
    }
  }
}