package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicsBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeCollectLatestFlow
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseViewBindingFragment<FragmentTopicsBinding, TopicsViewModel>(FragmentTopicsBinding::inflate) {

    override val viewModel: TopicsViewModel  by viewModels()

    private val topicAdapter: PagedTopicAdapter by lazy {
        PagedTopicAdapter().apply {
            onTopicSelected = {
                findNavController().safeNavigate(TopicsFragmentDirections.actionTopicsFragmentToTopicDetailFragment(topic = it))
                //viewModel.testDelete()
            }
        }
    }

    override fun initView() {
        with(viewBinding) {
            rvTopics.layoutManager = LinearLayoutManager(requireContext())
            rvTopics.adapter = topicAdapter
        }
    }

    override suspend fun subscribeData() {
        safeCollectLatestFlow(viewModel.topics) {
            topicAdapter.submitData(it)
        }
    }
}
