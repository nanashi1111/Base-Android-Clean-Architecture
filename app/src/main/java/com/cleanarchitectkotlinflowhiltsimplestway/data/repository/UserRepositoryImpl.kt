package com.cleanarchitectkotlinflowhiltsimplestway.data.repository

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.User
import com.cleanarchitectkotlinflowhiltsimplestway.data.remote.Api
import com.cleanarchitectkotlinflowhiltsimplestway.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: Api) : UserRepository {
    override suspend fun getUser(): List<User>  = api.sampleGet()
}