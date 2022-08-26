package com.cleanarchitectkotlinflowhiltsimplestway.domain.repository

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.UserEntity

interface UserRepository {

   suspend fun getUser(): List<UserEntity>

}