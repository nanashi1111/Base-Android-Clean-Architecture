package com.cleanarchitectkotlinflowhiltsimplestway.domain.models

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.UserEntity

data class User(val name: String) {
  companion object {
    fun fromEntity(entity: UserEntity) = User(entity.name)
  }
}