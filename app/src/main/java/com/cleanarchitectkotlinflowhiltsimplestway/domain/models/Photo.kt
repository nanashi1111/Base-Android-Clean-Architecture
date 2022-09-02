package com.cleanarchitectkotlinflowhiltsimplestway.domain.models

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.PhotoEntity
import java.io.Serializable

data class Photo (val id: String, val raw: String, val full: String, val thumb: String): Serializable {

  var topLeftRadius: Float = 15f
  var topRightRadius: Float = 15f
  var bottomLeftRadius: Float = 15f
  var bottomRightRadius: Float = 15f

  companion object {
    const val PHOTO_LIST_RADIUS = 5f
    fun fromEntity(entity: PhotoEntity): Photo {
      return Photo(
        id = entity.id ?: "",
        raw = entity.urls?.raw ?: "",
        full = entity.urls?.full ?: "",
        thumb = entity.urls?.thumb ?: ""
      )
    }
  }
}