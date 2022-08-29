package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import androidx.lifecycle.*
import androidx.paging.*
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.GetTopics
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(getTopics: GetTopics) :
  BaseViewModel() {

  val topics: Flow<PagingData<Topic>> = getTopics.invoke().cachedIn(viewModelScope)

}