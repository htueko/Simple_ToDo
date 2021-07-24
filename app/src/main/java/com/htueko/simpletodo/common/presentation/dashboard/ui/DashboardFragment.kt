package com.htueko.simpletodo.common.presentation.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.htueko.simpletodo.common.presentation.base.BaseFragment
import com.htueko.simpletodo.common.presentation.dashboard.viewmodel.DashboardViewModel
import com.htueko.simpletodo.common.util.State
import com.htueko.simpletodo.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
    }

    private fun setupUI() {

    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.getTodos()
            viewModel.todos
                .collect { state ->
                    when (state) {
                        is State.Error -> {
                            showProgress(false)
                            showError(state.message, true)
                        }
                        is State.Loading -> {
                            showProgress(true)
                            showError(null, false)
                        }
                        is State.Success -> {
                            showProgress(false)
                            showError(null, false)
                        }
                    }
                }
        }
    }

    private fun showProgress(value: Boolean) {
        binding.progressDashboard.isVisible = value
    }

    private fun showError(text: String?, value: Boolean) {
        if (value) {
            binding.tvNoDataDashboard.text = text
        } else {
            binding.tvNoDataDashboard.isVisible = value
        }
    }

    private fun toDetail() {
        binding.fabDashboard.setOnClickListener {

        }
    }

}