package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.ItemTopicBinding
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Topic
import com.dtv.starter.presenter.utils.extension.loadImageFitToImageView

class TopicsAdapter(val topics: MutableList<Topic>): RecyclerView.Adapter<TopicsAdapter.TopicViewHolder>() {

  class TopicViewHolder (val binding: ItemTopicBinding): RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
    return TopicViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_topic, parent, false))
  }

  override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
    holder.binding.topic = topics[position]
    holder.binding.executePendingBindings()
  }

  override fun getItemCount() = topics.size

  fun append(newTopics: List<Topic>){
    val position = topics.size
    topics.addAll(newTopics)
    notifyItemRangeInserted(position, newTopics.size)
  }
}

@BindingAdapter("bindTopicCover")
fun AppCompatImageView.bindTopicCover(cover: String) {
  loadImageFitToImageView(cover)
}