package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.User
import com.cleanarchitectkotlinflowhiltsimplestway.domain.exception.resolveError
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.SampleUseCase
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sampleUseCase: SampleUseCase) :
    ViewModel() {

    val result: MutableLiveData<List<User>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getSampleResponse() {
        viewModelScope.launch {
            sampleUseCase.invoke(Unit).collect {
                when (it) {
                    is State.LoadingState -> loading.postValue(true)
                    is State.ErrorState -> {
                        loading.postValue(false)
                        Log.d("ErrorAPI", "${it.exception.resolveError()}")

                    }
                    is State.DataState -> {
                        result.postValue(it.data)
                        Log.d("SuccessAPI", "${it.data.size}")

                    }
                }
            }
        }
    }


}