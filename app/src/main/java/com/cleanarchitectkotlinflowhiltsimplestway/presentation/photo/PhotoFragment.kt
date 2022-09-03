package com.cleanarchitectkotlinflowhiltsimplestway.presentation.photo

import android.Manifest
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentPhotoBinding
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeCollectFlow
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeCollectLatestFlow
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment: BaseViewBindingFragment<FragmentPhotoBinding, PhotoViewModel>(FragmentPhotoBinding::inflate) {

  override val viewModel: PhotoViewModel by viewModels()

  private val args: PhotoFragmentArgs by navArgs()

  override fun initView() {
    viewBinding.apply {
      ivBack.setOnClickListener { findNavController().navigateUp() }
      ivDownload.setOnClickListener { downloadPhoto(args.photo) }
      displayPhoto(args.photo)
    }
  }

  private fun downloadPhoto(photo: Photo){
    Dexter.withContext(requireContext())
      .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
      .withListener(object : MultiplePermissionsListener{
        override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
          if (p0?.areAllPermissionsGranted() == true) {
            viewModel.downloadPhoto(photo)
          }
        }

        override fun onPermissionRationaleShouldBeShown(p0: MutableList<PermissionRequest>?, p1: PermissionToken?) {
        }

      })
      .check()
  }

  private fun displayPhoto(photo: Photo) {
    viewBinding.ivImage.apply {
      Glide.with(requireContext()).clear(this)
      Glide.with(context).load(photo.full)
        .error(R.drawable.ic_loading_non_rounded_error)
        .transition(DrawableTransitionOptions.withCrossFade()).into(this)
    }
  }

  override suspend fun subscribeData() {
    safeCollectFlow(viewModel.downloadPhotoResult) {
      viewBinding.pbLoading.beVisibleIf(it is State.LoadingState)
      if (it is State.DataState) {
        Toast.makeText(requireContext(), it.data, Toast.LENGTH_LONG).show()
      }
    }
  }
}