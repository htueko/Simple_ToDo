package com.htueko.simpletodo.common.presentation.add.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.htueko.simpletodo.R
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.presentation.add.viewmodel.AddViewModel
import com.htueko.simpletodo.common.presentation.base.BaseFragment
import com.htueko.simpletodo.common.util.State
import com.htueko.simpletodo.common.util.showSnackBar
import com.htueko.simpletodo.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding, AddViewModel>() {

    override val viewModel: AddViewModel by viewModels()
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddBinding = FragmentAddBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSaveButtonClicked()
        observeData()
    }

    private fun validateInput(): Todo {
        val title = binding.edtTitle.text.toString().trim()
        val desc = binding.edtTitle.text.toString().trim()
        if (title.isEmpty()) {
            showTitleValidateError(R.string.error_title)
        }
        val important = binding.chkIsImportant.isChecked
        val urgent = binding.chkIsUrgent.isChecked
        val current = Clock.System.now()
        val dateTime = current.toLocalDateTime(TimeZone.currentSystemDefault())
        return Todo(
            id = 1L,
            title = title,
            desc = desc.orEmpty(),
            hasComplete = false,
            isImportant = important,
            isUrgent = urgent,
            createAt = dateTime,
            updateAt = dateTime
        )
    }

    private fun handleSaveButtonClicked() {
        binding.btnSave.setOnClickListener {
            val data = validateInput()
            viewModel.addTodo(data)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.updateUIFlow
                .collect { state ->
                    Toast.makeText(requireContext(), "$state", Toast.LENGTH_SHORT).show()
                    when (state) {
                        is State.Error -> {
                            showProgress(false)
                            showInfoSnack(true, R.string.error_save)
                        }
                        is State.Loading -> {
                            showProgress(true)
                            showInfoSnack(false, null)
                        }
                        is State.Success -> {
                            showProgress(false)
                            showInfoSnack(true, R.string.success_save)
                            toDashboard()
                        }
                    }
                }
        }
    }

    private fun showProgress(value: Boolean) {
        binding.progressAdd.isVisible = value
    }

    private fun showInfoSnack(value: Boolean, message: Int?) {
        if (value) {
            binding.root.showSnackBar(message!!)
        }
    }

    private fun showTitleValidateError(message: Int?) {
        binding.tfTitle.apply {
            error = resources.getString(message!!)
            binding.edtTitle.requestFocus()
        }
    }

    private fun toDashboard() {
        findNavController().navigate(R.id.action_addFragment_to_dashboardFragment)
    }
}
