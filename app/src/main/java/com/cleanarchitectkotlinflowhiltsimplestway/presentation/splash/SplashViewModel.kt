package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import androidx.lifecycle.*
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.UserEntity
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.User
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.GetUserList
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getUserList: GetUserList) :
  BaseViewModel() {

  val users = MutableSharedFlow<State<List<User>>>()

  fun getSampleResponse() = viewModelScope.launch {
    getUserList(Unit).collect {
      users.emit(it)
    }
  }

}