package com.dnieln7.testing.ui.hilt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.testing.databinding.ListTileMissionBinding
import com.dnieln7.testing.model.spacex.Mission

class MissionAdapter: ListAdapter<Mission, MissionAdapter.MissionViewHolder>(MissionDiffUtil()) {

    inner class MissionViewHolder(private val binding: ListTileMissionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mission: Mission) {
            binding.name.text = mission.missionName
            binding.description.text = mission.description
        }
    }

    private class MissionDiffUtil : DiffUtil.ItemCallback<Mission>() {
        override fun areItemsTheSame(oldItem: Mission, newItem: Mission): Boolean {
            return oldItem.missionId == newItem.missionId
        }

        override fun areContentsTheSame(oldItem: Mission, newItem: Mission): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        return MissionViewHolder(
            ListTileMissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

