package com.project.myapplication.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.project.myapplication.databinding.FragmentSecondBinding
import com.project.myapplication.ui.base.BaseFragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : BaseFragment<FragmentSecondBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
            .load(getImageUrl())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivPhoto)
    }

    private fun getImageUrl(): String {
        return arguments?.getString("url_key", "")?:""
    }

    override fun getBindingInflater(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        attachParent: Boolean
    ): FragmentSecondBinding {
        return FragmentSecondBinding.inflate(layoutInflater, viewGroup, attachParent)
    }

}