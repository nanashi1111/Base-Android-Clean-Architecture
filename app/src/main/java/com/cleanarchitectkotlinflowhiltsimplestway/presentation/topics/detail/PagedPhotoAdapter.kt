package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.ItemPhotoBinding
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.dtv.starter.presenter.utils.extension.loadImageFitToImageViewWithCorder

class PagedPhotoAdapter (val onPhotoSelected: (Photo) -> Unit): PagingDataAdapter<Photo, PagedPhotoAdapter.PhotoViewHolder>(object :DiffUtil.ItemCallback<Photo>() {
  override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
    return oldItem.raw == newItem.raw
  }

  override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
    return oldItem == newItem
  }

}) {

  class PhotoViewHolder (val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

  override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
    val photo = getItem(position)
    holder.binding.photo = photo
    holder.binding.executePendingBindings()
    holder.binding.root.setOnClickListener {
      photo?.let { onPhotoSelected(it) }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
    return PhotoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false))
  }
}

@BindingAdapter(value = ["path", "topLeftCorner", "topRightCorner", "bottomLeftCorner", "bottomRightCorner"], requireAll = true)
fun AppCompatImageView.bindPhoto(path: String, topLeftCorner: Float, topRightCorner: Float, bottomLeftCorner: Float, bottomRightCorner: Float){
  loadImageFitToImageViewWithCorder(path, topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner)
}