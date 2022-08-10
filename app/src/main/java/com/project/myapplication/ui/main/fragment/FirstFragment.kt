package com.project.myapplication.ui.main.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.project.myapplication.R
import com.project.myapplication.databinding.FragmentFirstBinding
import com.project.myapplication.model.resources.Resources
import com.project.myapplication.ui.base.BaseFragment
import com.project.myapplication.ui.main.recyclerview.PhotoListAdapter
import com.project.myapplication.ui.main.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    private val photoViewModel: PhotoViewModel by viewModels()

    private val photoListAdapter by lazy { PhotoListAdapter() {
        val bundle = Bundle().apply {
            putString("url_key", it.urls.regular)
        }
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    } }

    private var isGrid = false

    private val gridLayoutManager by lazy { GridLayoutManager(context, 1) }

    override fun getBindingInflater(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        attachParent: Boolean
    ): FragmentFirstBinding {
        return  FragmentFirstBinding.inflate(layoutInflater, viewGroup, attachParent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOptionMenu()
        initRecyclerView()
        initObserver()
        photoViewModel.retrievePhotoData()
    }

    private fun initOptionMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_switch -> {
                        if (isGrid) {
                            gridLayoutManager.spanCount = 1
                        } else {
                            gridLayoutManager.spanCount = 2
                        }
                        isGrid = !isGrid
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initRecyclerView() {
        binding.rvPhotosList.adapter = photoListAdapter
        binding.rvPhotosList.layoutManager = gridLayoutManager
    }

    private fun initObserver() {
        photoViewModel.photoList.observe(viewLifecycleOwner) {
            when (it) {
                is Resources.Success -> {
                    photoListAdapter.submitData(lifecycle, it.data)
                }
                is Resources.Error -> {
                    Toast.makeText(context, it.errorException.message?:"", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

}