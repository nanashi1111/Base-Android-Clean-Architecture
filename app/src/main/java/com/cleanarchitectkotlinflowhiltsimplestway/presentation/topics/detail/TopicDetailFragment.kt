package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicDetailBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeCollectLatestFlow
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeNavigate
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicDetailFragment: BaseViewBindingFragment<FragmentTopicDetailBinding, TopicDetailViewModel>(FragmentTopicDetailBinding::inflate) {

  override val viewModel: TopicDetailViewModel by viewModels()

  private val args: TopicDetailFragmentArgs by navArgs()

  private val photoAdapter = PagedPhotoAdapter {
    findNavController().safeNavigate(
      TopicDetailFragmentDirections.actionTopicDetailFragmentToPhotoFragment(it)
    )
  }

  private val concatAdapter: ConcatAdapter by lazy {
    val headerAdapter = HeaderAdapter(args.topic)
    ConcatAdapter(headerAdapter, photoAdapter)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycleScope.launchWhenCreated {
      viewModel.initTopicData(args.topic)
    }
  }

  override fun initView() {
    with(viewBinding) {
      //Toolbar
      layoutToolbar.apply {
        ivBack.setOnClickListener { findNavController().navigateUp() }
        tvTitle.text = args.topic.title
      }

      //Photo list
      rvImages.apply {
        adapter = concatAdapter
        layoutManager = GridLayoutManager(requireContext(),3).apply {
          spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
              return if (position == 0) {
                3
              } else {
                1
              }
            }
          }
        }
      }
    }
  }

  override suspend fun subscribeData() {
    safeCollectLatestFlow(viewModel.photos) {
      photoAdapter.submitData(it)
    }
    safeCollectLatestFlow(photoAdapter.loadStateFlow) {
      viewBinding.pbLoading.beVisibleIf(it.refresh is LoadState.Loading)
    }
  }
}