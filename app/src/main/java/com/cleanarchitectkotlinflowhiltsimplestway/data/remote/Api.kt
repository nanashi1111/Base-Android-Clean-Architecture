package com.cleanarchitectkotlinflowhiltsimplestway.data.remote

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.UserEntity
import retrofit2.http.GET

interface Api {
    @GET("users")
    suspend fun getUsers(): List<UserEntity>
}