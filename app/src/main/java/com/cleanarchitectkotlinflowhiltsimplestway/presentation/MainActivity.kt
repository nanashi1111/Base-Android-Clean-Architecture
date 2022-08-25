package com.cleanarchitectkotlinflowhiltsimplestway.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.TopicsFragment
import com.dtv.starter.presenter.utils.extension.add
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    add(TopicsFragment(), addToBackStack = false)
  }
}