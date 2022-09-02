package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import androidx.lifecycle.*
import androidx.paging.*
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.GetTopics
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(getTopics: GetTopics) :
  BaseViewModel() {

  val deleteEvent = MutableStateFlow(0L)

   val topics = getTopics().cachedIn(viewModelScope).combine(deleteEvent) { data: PagingData<Topic>, event: Long ->
    Logger.d("Combine: $event")
    data.filter {
      if (event > 0) {
        it.title.startsWith("F")
      } else {
        true
      }
    }
  }

  fun testDelete() {
    viewModelScope.launch {
      deleteEvent.value++
    }
  }

}