package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentSplashBinding
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.State
import com.dtv.starter.presenter.base.BaseFragment
import com.dtv.starter.presenter.utils.extension.beVisibleIf
import com.dtv.starter.presenter.utils.extension.toast
import com.dtv.starter.presenter.utils.log.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override val mViewModel: SplashViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_splash

    override fun initView() {
        requireActivity().toast("Clicked")
        mDataBinding.viewModel = mViewModel
        mDataBinding.textView1.setOnClickListener {
            requireActivity().toast("Clicked")
            mViewModel.getSampleResponse()
        }
    }

    override suspend fun subscribeData() {
        mViewModel.users.collect {
            mDataBinding.pbLoading.beVisibleIf(it is State.LoadingState)
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
