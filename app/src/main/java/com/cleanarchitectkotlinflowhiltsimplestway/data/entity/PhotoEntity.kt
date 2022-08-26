package com.cleanarchitectkotlinflowhiltsimplestway.data.entity

import com.google.gson.annotations.SerializedName

data class PhotoEntity(
  @SerializedName("id") var id: String? = null,
  @SerializedName("created_at") var createdAt: String? = null,
  @SerializedName("updated_at") var updatedAt: String? = null,
  @SerializedName("promoted_at") var promotedAt: String? = null,
  @SerializedName("width") var width: Int? = null,
  @SerializedName("height") var height: Int? = null,
  @SerializedName("color") var color: String? = null,
  @SerializedName("blur_hash") var blurHash: String? = null,
  @SerializedName("description") var description: String? = null,
  @SerializedName("alt_description") var altDescription: String? = null,
  @SerializedName("urls") var urls: UrlsEntity? = UrlsEntity()
)