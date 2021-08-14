package com.cleanarchitectkotlinflowhiltsimplestway.presentation.splash

import android.os.Bundle
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.dtv.starter.presenter.base.BaseActivity
import com.dtv.starter.presenter.utils.extension.add
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        add(SplashFragment(), addToBackStack = false)
    }

}