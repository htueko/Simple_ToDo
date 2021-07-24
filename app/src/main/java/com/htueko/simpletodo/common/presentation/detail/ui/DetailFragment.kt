package com.htueko.simpletodo.common.presentation.detail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.htueko.simpletodo.common.presentation.base.BaseFragment
import com.htueko.simpletodo.common.presentation.detail.viewmodel.DetailViewModel
import com.htueko.simpletodo.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        TODO("Not yet implemented")
    }

    override val viewModel: DetailViewModel
        get() = TODO("Not yet implemented")
}