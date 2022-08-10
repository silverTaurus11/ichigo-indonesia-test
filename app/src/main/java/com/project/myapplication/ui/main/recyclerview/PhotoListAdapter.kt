package com.project.myapplication.ui.main.recyclerview

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.project.myapplication.model.photo.Photo

class PhotoListAdapter(private val onClick:(Photo)-> Unit): PagingDataAdapter<Photo,
        PhotoGridHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: PhotoGridHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridHolder {
        return PhotoGridHolder.create(parent)
    }

}