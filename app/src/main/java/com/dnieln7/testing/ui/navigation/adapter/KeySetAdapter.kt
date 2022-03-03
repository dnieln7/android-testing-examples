package com.dnieln7.testing.ui.navigation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.testing.databinding.GridTileKeySetBinding
import com.dnieln7.testing.model.KeySet

class KeySetAdapter(
    private val data: List<KeySet>,
    private val onClick: (KeySet) -> Unit
) : RecyclerView.Adapter<KeySetAdapter.KeySetViewHolder>() {

    inner class KeySetViewHolder(private val binding: GridTileKeySetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keySet: KeySet) {
            binding.name.text = keySet.name
            binding.root.setOnClickListener { onClick(keySet) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeySetViewHolder {
        return KeySetViewHolder(
            GridTileKeySetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: KeySetViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}