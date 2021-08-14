package com.cleanarchitectkotlinflowhiltsimplestway.domain.repository

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

   suspend fun getUser(): List<User>

}