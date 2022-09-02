package com.cleanarchitectkotlinflowhiltsimplestway.presentation.photo

import androidx.lifecycle.viewModelScope
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.cleanarchitectkotlinflowhiltsimplestway.domain.usecase.DownloadPhoto
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val downloadPhoto: DownloadPhoto) : BaseViewModel() {

  private val _downloadPhotoResult = MutableSharedFlow<State<String>>()
  val downloadPhotoResult: SharedFlow<State<String>> = _downloadPhotoResult

  fun downloadPhoto(photo: Photo) {
    viewModelScope.launch {
      downloadPhoto.invoke(DownloadPhoto.Params(photo.full, "${photo.id}.JPEG")).collectLatest {
        _downloadPhotoResult.emit(it)
      }
    }
  }
}