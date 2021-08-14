package com.dtv.starter.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.cleanarchitectkotlinflowhiltsimplestway.utils.extension.isInternetAvailable
import com.dtv.starter.presenter.utils.extension.displayKeyboard
import com.dtv.starter.presenter.utils.log.Logger

abstract class BaseFragment<Binding, BViewModel> :
    Fragment() where Binding : ViewDataBinding, BViewModel : ViewModel {

    protected lateinit var mDataBinding: Binding
    protected abstract val mViewModel: BViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("OnCreate ${tag}")
        subscribeData()

    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (!::mDataBinding.isInitialized) {
            initDataBinding(inflater, container)
            initView()

        }
        return mDataBinding.root;
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mDataBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDataBinding.lifecycleOwner = this
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onPause() {
        super.onPause()
        activity?.displayKeyboard(false)
    }

    override fun onResume() {
        super.onResume()

    }


    protected var isShowDialog = true

    protected abstract fun layoutId(): Int
    protected abstract fun initView()
    protected abstract fun subscribeData()
}