package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicsBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeCollectLatestFlow
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeNavigate
import com.dtv.starter.presenter.utils.extension.beGone
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseViewBindingFragment<FragmentTopicsBinding, TopicsViewModel>(FragmentTopicsBinding::inflate) {

    override val viewModel: TopicsViewModel  by viewModels()

    private val topicAdapter: PagedTopicAdapter by lazy {
        PagedTopicAdapter().apply {
            onTopicSelected = {
                findNavController().safeNavigate(TopicsFragmentDirections.actionTopicsFragmentToTopicDetailFragment(topic = it))
            }
        }
    }

    override fun initView() {
        with(viewBinding) {
            rvTopics.layoutManager = LinearLayoutManager(requireContext())
            rvTopics.adapter = topicAdapter
            with(layoutToolbar) {
                ivBack.beGone()
                tvTitle.text = getString(R.string.topics)
            }
        }
    }

    override suspend fun subscribeData() {
        safeCollectLatestFlow(viewModel.topics) {
            topicAdapter.submitData(it)
        }
        safeCollectLatestFlow(topicAdapter.loadStateFlow) {
            when (it.refresh) {
                is LoadState.Loading -> Logger.d("Loading photos")
                is LoadState.Error -> Logger.d("Loading photos error: ${(it.refresh as LoadState.Error).error}")
                else -> {}
            }
        }
    }
}
