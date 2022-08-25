package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import androidx.fragment.app.viewModels
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentSplashBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.base.BaseViewBindingFragment
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseViewBindingFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {

    override val viewModel: SplashViewModel  by viewModels()

    override fun initView() {
        viewBinding.textView1.setOnClickListener {
            viewModel.getSampleResponse()
        }
    }

    override suspend fun subscribeData() {
        viewModel.users.collect {
            viewBinding.pbLoading.beVisibleIf(it is State.LoadingState)
            when (it) {
                is State.DataState -> {
                    Logger.d("UserCount = ${it.data.size}")
                }
                is State.ErrorState -> {
                    Logger.d("UserError: ${it.exception}")
                }
            }
        }
    }
}
