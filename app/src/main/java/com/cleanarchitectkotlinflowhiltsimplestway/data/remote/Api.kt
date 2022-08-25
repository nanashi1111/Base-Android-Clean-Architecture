package com.cleanarchitectkotlinflowhiltsimplestway.data.remote

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.TopicEntity
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.UserEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("users")
    suspend fun getUsers(): List<UserEntity>

    @GET("topics")
    suspend fun getTopics(@Query("page") page: Int): List<TopicEntity>
}