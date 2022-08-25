package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentTopicsBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseViewBindingFragment<FragmentTopicsBinding, TopicsViewModel>(FragmentTopicsBinding::inflate) {

    override val viewModel: TopicsViewModel  by viewModels()

    private val topicAdapter: TopicsAdapter by lazy {
        TopicsAdapter(mutableListOf())
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
                    //Toast.makeText(requireActivity(), "Topic count: ${it.data.size}", Toast.LENGTH_LONG).show()
                }
                is State.ErrorState -> {
                    Logger.d("UserError: ${it.exception}")
                }
            }
        }
    }
}
