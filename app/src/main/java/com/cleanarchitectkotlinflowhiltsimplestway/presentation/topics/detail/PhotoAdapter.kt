package com.cleanarchitectkotlinflowhiltsimplestway.presentation.topics.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cleanarchitectkotlinflowhiltsimplestway.R
import com.cleanarchitectkotlinflowhiltsimplestway.databinding.ItemPhotoBinding
import com.cleanarchitectkotlinflowhiltsimplestway.domain.models.Photo
import com.dtv.starter.presenter.utils.extension.loadImageFitToImageViewWithCorder

class PhotoAdapter (val photos: MutableList<Photo>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

  class PhotoViewHolder (val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
    return PhotoViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false))
  }

  override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
    holder.binding.photo = photos[position]
    holder.binding.executePendingBindings()
  }

  override fun getItemCount() = photos.size

  fun append(newPhotos: List<Photo>) {
    photos.addAll(newPhotos)
    notifyDataSetChanged()
  }
}

@BindingAdapter(value = ["path", "topLeftCorner", "topRightCorner", "bottomLeftCorner", "bottomRightCorner"], requireAll = true)
fun AppCompatImageView.bindPhoto(path: String, topLeftCorner: Float, topRightCorner: Float, bottomLeftCorner: Float, bottomRightCorner: Float){
  loadImageFitToImageViewWithCorder(path, topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner)
}