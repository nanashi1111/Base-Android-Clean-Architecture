package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleanarchitectkotlinflowhiltsimplestway.data.entity.State
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicsBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeNavigate
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseViewBindingFragment<FragmentTopicsBinding, TopicsViewModel>(FragmentTopicsBinding::inflate) {

    override val viewModel: TopicsViewModel  by viewModels()

    private val topicAdapter: TopicsAdapter by lazy {
        TopicsAdapter(mutableListOf()) {
            findNavController().safeNavigate(TopicsFragmentDirections.actionTopicsFragmentToTopicDetailFragment(topic = it))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted { viewModel.getTopics() }
    }

    override fun initView() {
        with(viewBinding) {
            rvTopics.layoutManager = LinearLayoutManager(requireContext())
            rvTopics.adapter = topicAdapter
        }
    }

    override suspend fun subscribeData() {
        viewModel.topics.collect {
            viewBinding.pbLoading.beVisibleIf(it is State.LoadingState)
            when (it) {
                is State.DataState -> {
                    topicAdapter.append(it.data)
                }
                is State.ErrorState -> {
                    Logger.d("UserError: ${it.exception}")
                }
                else -> {}
            }
        }
    }
}
