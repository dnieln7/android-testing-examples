package com.dnieln7.testing.ui.navigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.testing.databinding.ListTileKeyBinding
import com.dnieln7.testing.model.KeySet

class KeyAdapter(private val data: List<String>) :
    RecyclerView.Adapter<KeyAdapter.KeyViewHolder>() {

    inner class KeyViewHolder(private val binding: ListTileKeyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(key: String) {
            binding.name.text = key
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyViewHolder {
        return KeyViewHolder(
            ListTileKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: KeyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}