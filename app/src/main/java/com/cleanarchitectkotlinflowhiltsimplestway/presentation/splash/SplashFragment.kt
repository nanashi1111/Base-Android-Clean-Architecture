package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.FragmentSplashBinding
import com.dtv.starter.presenter.base.BaseFragment
import com.dtv.starter.presenter.utils.extension.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override val mViewModel: SplashViewModel by viewModels()

    override fun layoutId() = R.layout.fragment_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getSampleResponse()
    }

    override fun initView() {

    }

    override fun subscribeData() {
        mViewModel.result.observe(this) {
            Log.d("Response", "Size: ${it.size}")
            requireActivity().toast("Users count: ${it.size}")
        }
    }

}
