package com.project.myapplication.ui.main.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.project.myapplication.R
import com.project.myapplication.databinding.ItemPhotoBinding
import com.project.myapplication.model.photo.Photo

class PhotoGridHolder(private val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): PhotoGridHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo,  parent,false)
            val binding = ItemPhotoBinding.bind(view)
            return PhotoGridHolder(binding)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(photo: Photo?, onClick:(Photo) -> Unit) {
        photo?.apply {
            Glide.with(binding.root).load(urls.regular)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivPhotoList)
            binding.tvAuthorList.text = "Author: ${user.name}"
            binding.tvDescriptionList.text = "Description: $description"
            binding.root.setOnClickListener {
                onClick(photo)
            }
        }
    }
}