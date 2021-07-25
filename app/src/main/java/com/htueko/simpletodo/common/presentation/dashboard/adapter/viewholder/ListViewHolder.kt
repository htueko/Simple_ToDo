package com.htueko.simpletodo.common.presentation.dashboard.adapter.viewholder

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.htueko.simpletodo.R
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.presentation.dashboard.ui.DashboardFragmentDirections
import com.htueko.simpletodo.databinding.ItemListBinding

class ListViewHolder(
    private val binding: ItemListBinding,
    private val onCheckChangeListener: (Todo) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(todo: Todo) {
        binding.apply {
            tvTitleList.text = todo.title
            tvDescList.text = todo.desc
            chkCompleteList.isChecked = todo.isImportant
            val res = binding.root.context.resources
            if (todo.isImportant) {
                binding.imvImportantList.setBackgroundColor(res.getColor(R.color.status_high))
            } else {
                binding.imvImportantList.setBackgroundColor(res.getColor(R.color.status_low))
            }
            if (todo.isUrgent) {
                binding.imvUrgentList.setBackgroundColor(res.getColor(R.color.status_high))
            } else {
                binding.imvUrgentList.setBackgroundColor(res.getColor(R.color.status_low))
            }
        }
        binding.chkCompleteList.setOnClickListener { onCheckChangeListener(todo) }
        binding.root.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(todo.id.toInt())
            it.findNavController()
                .navigate(action)
        }
    }

}