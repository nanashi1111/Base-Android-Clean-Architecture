package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicDetailBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeCollectFlow
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import com.dtv.starter.presenter.utils.extension.loadImageFitToImageView
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicDetailFragment: BaseViewBindingFragment<FragmentTopicDetailBinding, TopicDetailViewModel>(FragmentTopicDetailBinding::inflate) {

  override val viewModel: TopicDetailViewModel by viewModels()

  private val args: TopicDetailFragmentArgs by navArgs()

  private val photoAdapter: PhotoAdapter by lazy {
    PhotoAdapter(mutableListOf())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycleScope.launchWhenStarted {
      viewModel.initTopicData(args.topic)
      viewModel.getTopicPhotos()
    }
  }

  override fun initView() {
    with(viewBinding) {
      //Toolbar
      layoutToolbar.toolbar.apply {
        title = "${args.topic.title}"
        setNavigationIcon(R.drawable.ic_icon_back)
        setNavigationOnClickListener {
          findNavController().navigateUp()
        }
      }

      //Photo list
      rvImages.apply {
        layoutManager = GridLayoutManager(requireContext(),3)
        adapter = photoAdapter
      }

      //header
      args.topic.apply {
        ivTopicCover.loadImageFitToImageView(previewImage)
        tvTitle.text = title
        tvDescription.text = description
      }

    }
  }

  override suspend fun subscribeData() {
    safeCollectFlow(viewModel.photos) {
      viewBinding.pbLoading.beVisibleIf(it is State.LoadingState)
      when (it) {
        is State.DataState -> {
          photoAdapter.append(it.data)
        }
        is State.ErrorState -> {
          Logger.d("UserError: ${it.exception}")
        }
        else -> {}
      }
    }
  }
}