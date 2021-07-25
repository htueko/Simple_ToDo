package com.htueko.simpletodo.common.presentation.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.htueko.simpletodo.R
import com.htueko.simpletodo.common.presentation.base.BaseFragment
import com.htueko.simpletodo.common.presentation.dashboard.adapter.AdapterConstant
import com.htueko.simpletodo.common.presentation.dashboard.adapter.AppAdapter
import com.htueko.simpletodo.common.presentation.dashboard.viewmodel.DashboardViewModel
import com.htueko.simpletodo.common.util.State
import com.htueko.simpletodo.common.util.showSnackBar
import com.htueko.simpletodo.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    private var layoutManger: GridLayoutManager? = null
    private lateinit var appAdapter: AppAdapter

    override val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate: called")
        setHasOptionsMenu(true)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated: called")
        updateData()
        setupMenu()
        setupMenuItemClickListener()
        setupRecyclerView()
        observeData()
        toAddFragment()
    }

    private fun setupMenu() {
        Timber.d("setupMenu: called")
        binding.toolbarDashboard.inflateMenu(R.menu.menu_dashboard)
    }

    private fun setupMenuItemClickListener() {
        Timber.d("setupMenuItemClickListener: called")
        binding.toolbarDashboard.setOnMenuItemClickListener { item ->
            Timber.d("setupMenuItemClickListener: called")
            Timber.d("setupMenuItemClickListener: ${item.itemId}")
            when (item.itemId) {
                R.id.action_list_view -> {
                    Timber.d("setupMenuItemClickListener: ${item.itemId}")
                    layoutManger?.spanCount = AdapterConstant.DEFAULT_SPAN_COUNT
                    true
                }
                R.id.action_grid_view -> {
                    Timber.d("setupMenuItemClickListener: ${item.itemId}")
                    layoutManger?.spanCount = AdapterConstant.GRID_SPAN_COUNT
                    true
                }
                else -> {
                    Timber.d("setupMenuItemClickListener: ${item.itemId}")
                    false
                }
            }
        }
    }

    private fun setupRecyclerView() {
        Timber.d("setupRecyclerView: called")
        layoutManger = GridLayoutManager(requireContext(), AdapterConstant.DEFAULT_SPAN_COUNT)
        appAdapter = AppAdapter(
            layoutManger,
            {
                viewModel.updateTodo(it)
                updateData()
            },
            {
                viewModel.deleteTodo(it)
                updateUi()
                updateData()
            }
        )
        binding.rvDashboard.apply {
            layoutManager = layoutManger
            adapter = appAdapter
        }
    }

    private fun observeData() {
        Timber.d("observeData: called")
        viewLifecycleOwner.lifecycleScope.launch {
            Timber.d("observeData: ${this.coroutineContext}")
            viewModel.todos
                .collect { state ->
                    Timber.d("observeData: state $state")
                    when (state) {
                        is State.Error -> {
                            Timber.d("observeData: state $state")
                            showProgress(false)
                            showError(state.message, true)
                        }
                        is State.Loading -> {
                            Timber.d("observeData: state $state")
                            showProgress(true)
                            showError(null, false)
                        }
                        is State.Success -> {
                            Timber.d("observeData: state $state")
                            showProgress(false)
                            showError(null, false)
                            if (state.data.isEmpty()) {
                                showNoData(true)
                            }
                            appAdapter.data = state.data
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

    private fun showNoData(value: Boolean) {
        if (value) {
            binding.tvNoDataDashboard.visibility = View.VISIBLE
        } else {
            binding.tvNoDataDashboard.visibility = View.GONE
        }
    }

    private fun updateUi() {
        binding.root.showSnackBar(R.string.text_delete_success)
    }

    private fun updateData() {
        Timber.d("updateData: called")
        lifecycleScope.launch {
            Timber.d("updateData: scope called")
            viewModel.getTodos()
        }
    }

    private fun toAddFragment() {
        Timber.d("toAddFragment: called")
        binding.fabDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addFragment)
        }
    }
}
