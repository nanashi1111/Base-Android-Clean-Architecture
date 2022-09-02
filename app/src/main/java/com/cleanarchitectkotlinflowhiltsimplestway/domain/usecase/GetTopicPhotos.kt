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
      config = PagingConfig(10, 5),
      pagingSourceFactory = { PhotoPagingDataSource(photoRepository, params.topicId) }
    ).flow.map {
      it.map {
        Photo.fromEntity(it)
      }
    }
  }

}

/*
class GetTopicPhotos @Inject constructor(private val photoRepository: PhotoRepository) : UseCase<List<Photo>, GetTopicPhotos.Params>() {

  class Params(val topicId: String, val page: Int)

  override fun buildFlow(param: Params): Flow<State<List<Photo>>> {
    */
/*play ground, use zip operator to zip 2 api calls then return the merged list*//*

    val f1 = flow {
      emit(photoRepository.getTopicDetail(param.topicId, param.page))
    }
    val f2 = flow {
      emit(photoRepository.getTopicDetail(param.topicId, 1 + param.page))
    }


    val photoFlow = f1.zip(f2) { photos1, photos2 ->
      val photos = mutableListOf<PhotoEntity>()
      photos.addAll(photos1)
      photos.addAll(photos2)
      photos
    }

    val result = photoFlow.flatMapLatest {
      flow<State<List<Photo>>> {
        val data = it.mapIndexed { index, photoEntity ->
          val photo = Photo.fromEntity(photoEntity)
          when (index) {
            0 -> photo.topLeftRadius = 15f
            2 -> photo.topRightRadius = 15f
            it.size - 3 -> photo.bottomLeftRadius = 15f
            it.size - 1 -> photo.bottomRightRadius = 15f
            else -> {}
          }
          photo
        }
        emit(State.DataState(data))
      }
    }

    return result

    */
/*return flow<State<List<Photo>>> {
      val data = photoRepository.getTopicDetail(param.topicId, param.page)
      val photos = data.mapIndexed { index, photoEntity ->
        val photo = Photo.fromEntity(photoEntity)
        when (index) {
          0 -> photo.topLeftRadius = 15f
          2 -> photo.topRightRadius = 15f
          data.size - 3 -> photo.bottomLeftRadius = 15f
          data.size - 1 -> photo.bottomRightRadius = 15f
          else -> {}
        }
        photo
      }
      emit(State.DataState(photos))
    }*//*

  }
}*/
