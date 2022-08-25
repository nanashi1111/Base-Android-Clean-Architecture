package com.cleanarchitectkotlinflowhiltsimplestway.domain.models

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.PhotoEntity

data class Photo (val raw: String, val full: String, val thumb: String) {
  companion object {
    fun fromEntity(entity: PhotoEntity): Photo {
      return Photo(
        raw = entity.urls?.raw ?: "",
        full = entity.urls?.full ?: "",
        thumb = entity.urls?.thumb ?: ""
      )
    }
  }
}