package com.project.myapplication.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T: ViewBinding>: Fragment() {

    private var _binding: ViewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBindingInflater(inflater, container, false)
        return requireNotNull(_binding).root
    }

    abstract fun getBindingInflater(layoutInflater: LayoutInflater,
                                    viewGroup: ViewGroup?, attachParent: Boolean): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}