package com.dnieln7.testing.ui.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.testing.databinding.ListTileCatBinding
import com.dnieln7.testing.model.cats.Cat

class CatAdapter(
    private val data: List<Cat>,
    private val onClick: (Int) -> Unit,
    private val onDelete: (Cat) -> Unit
) : ListAdapter<Cat, CatAdapter.CatViewHolder>(CatDiffUtil()) {

    inner class CatViewHolder(private val binding: ListTileCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            binding.name.text = cat.name
            binding.root.setOnClickListener { onClick(cat.id) }
            binding.delete.setOnClickListener { onDelete(cat) }
        }
    }

    private class CatDiffUtil : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ListTileCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

