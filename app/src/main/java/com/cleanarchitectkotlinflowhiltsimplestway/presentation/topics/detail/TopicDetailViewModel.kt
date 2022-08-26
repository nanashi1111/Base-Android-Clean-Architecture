package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.detail

import androidx.lifecycle.viewModelScope
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.GetTopicPhotos
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicDetailViewModel @Inject constructor(private val getTopicPhotos: GetTopicPhotos): BaseViewModel() {

  lateinit var topic: Topic
  private var currentPage = 1

  private val _photos = MutableSharedFlow<State<List<Photo>>>()
  val photos: Flow<State<List<Photo>>> = _photos

  fun initTopicData(topic: Topic) {
    this.topic = topic
  }

  fun getTopicPhotos() {
    viewModelScope.launch (Dispatchers.IO) {
      val params = GetTopicPhotos.Params(topic.id, currentPage)
      getTopicPhotos(params).collect {
        _photos.emit(it)
      }
    }
  }

}