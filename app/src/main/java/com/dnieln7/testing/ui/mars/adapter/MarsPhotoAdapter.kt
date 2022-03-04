package com.dnieln7.testing.ui.mars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dnieln7.testing.R
import com.dnieln7.testing.databinding.GridTileMarsPhotoBinding
import com.dnieln7.testing.model.mars.MarsPhoto

class MarsPhotoAdapter(private val data: List<MarsPhoto>) :
    RecyclerView.Adapter<MarsPhotoAdapter.MarsViewHolder>() {

    inner class MarsViewHolder(private val binding: GridTileMarsPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marsPhoto: MarsPhoto) {
            val uri = marsPhoto.url.toUri().buildUpon().scheme("https")?.build()

            binding.id.text = marsPhoto.id
            binding.image.load(uri) {
                crossfade(true)
                placeholder(R.drawable.image_progress_animated)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        return MarsViewHolder(
            GridTileMarsPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}