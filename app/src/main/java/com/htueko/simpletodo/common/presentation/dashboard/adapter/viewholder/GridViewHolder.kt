package com.htueko.simpletodo.common.presentation.dashboard.adapter.viewholder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.htueko.simpletodo.R
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.presentation.dashboard.ui.DashboardFragmentDirections
import com.htueko.simpletodo.databinding.ItemGridBinding

class GridViewHolder(
    private val binding: ItemGridBinding,
    private val onCheckChangeListener: (Todo) -> Unit,
    private val onDeleteItemClickListener: (Todo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(todo: Todo) {
        binding.apply {
            tvTitleGrid.text = todo.title
            tvDescGrid.text = todo.desc
            chkCompleteGrid.isChecked = todo.isImportant
            val res = binding.root.context.resources
            if (todo.isImportant) {
                binding.imvImportantGrid.setBackgroundColor(res.getColor(R.color.status_high))
            } else {
                binding.imvImportantGrid.setBackgroundColor(res.getColor(R.color.status_low))
            }
            if (todo.isUrgent) {
                binding.imvUrgentGrid.setBackgroundColor(res.getColor(R.color.status_high))
            } else {
                binding.imvUrgentGrid.setBackgroundColor(res.getColor(R.color.status_low))
            }
        }
        binding.chkCompleteGrid.setOnClickListener { onCheckChangeListener(todo) }
        binding.imvDeleteGrid.setOnClickListener { onDeleteItemClickListener(todo) }
        binding.root.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(todo.id.toInt())
            it.findNavController()
                .navigate(action)
        }
    }
}
