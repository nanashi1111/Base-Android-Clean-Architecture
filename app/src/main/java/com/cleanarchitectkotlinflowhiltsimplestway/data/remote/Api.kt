package com.cleanarchitectkotlinflowhiltsimplestway.data.remote

import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.User
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {
    @GET("users")
    suspend fun sampleGet(): List<User>
}