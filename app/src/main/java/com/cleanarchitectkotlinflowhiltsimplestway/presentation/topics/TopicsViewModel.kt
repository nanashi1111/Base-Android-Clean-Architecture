package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import androidx.lifecycle.*
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.GetTopics
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(private val getTopics: GetTopics) :
  BaseViewModel() {

  val topics = MutableSharedFlow<State<List<Topic>>>()

  fun getTopics() = viewModelScope.launch {
    getTopics(1).collect{
      topics.emit(it)
    }
  }

}