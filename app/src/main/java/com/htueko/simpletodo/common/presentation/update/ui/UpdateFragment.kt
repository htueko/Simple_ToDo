package com.htueko.simpletodo.common.presentation.update.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.htueko.simpletodo.R
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.presentation.base.BaseFragment
import com.htueko.simpletodo.common.presentation.update.viewmodel.UpdateViewModel
import com.htueko.simpletodo.common.util.State
import com.htueko.simpletodo.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding, UpdateViewModel>() {

    private val args: UpdateFragmentArgs by navArgs()

    override val viewModel: UpdateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        getData()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUpdateBinding = FragmentUpdateBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        setupMenuItemClickListener()
        observeData()
    }

    private fun setupMenu() {
        binding.toolbarDetail.inflateMenu(R.menu.menu_update)
    }

    private fun setupMenuItemClickListener() {
        binding.toolbarDetail.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_update -> {

                    true
                }
                R.id.action_delete -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun getData() {
        // Todo update the todo id
        // val todoId = args.todoId.toLong()
        val todoId = 1.toLong()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTodoById(todoId)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todo
                .collect { state ->
                    when (state) {
                        is State.Error -> {
                            showProgress(false)
                        }
                        is State.Loading -> {
                            showProgress(true)
                        }
                        is State.Success -> {
                            showProgress(false)
                            val todo = state.data
                            updateUi(todo)
                        }
                    }
                }
        }
    }

    private fun showProgress(value: Boolean) {
        binding.progressDetail.isVisible = value
    }

    private fun updateUi(todo: Todo) {
        binding.apply {
            edtTitle.setText(todo.title)
            edtDescription.setText(todo.desc)
            chkIsImportant.isChecked = todo.isImportant
            chkIsUrgent.isChecked = todo.isUrgent
        }
    }

    private fun saveOnClickListener() {
        binding.btnSave.setOnClickListener {
        }
    }

    private fun toDashboard() {
        findNavController().navigate(R.id.action_detailFragment_to_dashboardFragment)
    }

    private fun validateInput() {
        val title = binding.edtTitle.text.toString().trim()
        val desc = binding.edtDescription.text.toString().trim()
        if (title.isEmpty()) {
            binding.tfTitle.error = resources.getString(R.string.error_title)
        }
        val important = binding.chkIsImportant.isChecked
        val urgent = binding.chkIsUrgent.isChecked
    }

    private fun saveData(todo: Todo) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.updateTodo(todo)
        }
    }
}
