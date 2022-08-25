package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicDetailBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicDetailFragment: BaseViewBindingFragment<FragmentTopicDetailBinding, TopicDetailViewModel>(FragmentTopicDetailBinding::inflate) {

  override val viewModel: TopicDetailViewModel by viewModels()

  private val args: TopicDetailFragmentArgs by navArgs()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycleScope.launchWhenStarted { viewModel.initTopicData(args.topic) }
  }

  override fun initView() {
    with(viewBinding) {
      layoutToolbar.toolbar.apply {
        title = "${args.topic.title}"
        setNavigationIcon(R.drawable.ic_icon_back)
        setNavigationOnClickListener {
          findNavController().navigateUp()
        }
      }
    }
  }

  override suspend fun subscribeData() {
  }
}