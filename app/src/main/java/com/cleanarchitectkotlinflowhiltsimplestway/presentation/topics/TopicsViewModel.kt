package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import androidx.lifecycle.*
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.GetTopics
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(private val getTopics: GetTopics) :
  BaseViewModel() {

  private val _topics = MutableSharedFlow<State<List<Topic>>>()
  val topics: Flow<State<List<Topic>>> = _topics

  fun getTopics() = viewModelScope.launch {
    getTopics(1).collect{
      _topics.emit(it)
    }
  }

}