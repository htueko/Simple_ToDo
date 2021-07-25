package com.htueko.simpletodo.common.presentation.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.htueko.simpletodo.common.domain.model.todo.Todo
import com.htueko.simpletodo.common.presentation.dashboard.adapter.viewholder.GridViewHolder
import com.htueko.simpletodo.common.presentation.dashboard.adapter.viewholder.ListViewHolder
import com.htueko.simpletodo.databinding.ItemGridBinding
import com.htueko.simpletodo.databinding.ItemListBinding

class AppAdapter(
    private val layoutManager: GridLayoutManager? = null,
    private val onChangeListener: (Todo) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        List,
        Grid
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    var data: List<Todo>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == AdapterConstant.DEFAULT_SPAN_COUNT) ViewType.List.ordinal
        else ViewType.Grid.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.List.ordinal -> {
                val binding =
                    ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ListViewHolder(binding, onChangeListener)
            }
            else -> {
                val binding =
                    ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GridViewHolder(binding, onChangeListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = data[position]
        when (holder.itemViewType) {
            ViewType.List.ordinal -> {
                (holder as ListViewHolder).bind(currentItem)
            }
            else -> {
                (holder as GridViewHolder).bind(currentItem)
            }
        }
    }


}