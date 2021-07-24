package com.htueko.simpletodo.common.presentation.detail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htueko.simpletodo.common.presentation.base.BaseFragment
import com.htueko.simpletodo.common.presentation.detail.viewmodel.DetailViewModel
import com.htueko.simpletodo.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding  = FragmentDetailBinding.inflate(inflater, container, false)


}