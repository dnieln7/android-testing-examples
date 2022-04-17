package com.dnieln7.testing.ui.swipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnieln7.testing.databinding.ListTileBookBinding
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.ui.hilt.adapter.BookAdapter
import com.google.common.util.concurrent.Futures.submit

class SwipeAdapter(
    private val onClick: (Book) -> Unit
) : ListAdapter<Book, SwipeAdapter.SwipeViewHolder>(SwipeDiffUtil()) {

    private val removedItems = mutableListOf<Book>()

    inner class SwipeViewHolder(private val binding: ListTileBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.title.text = book.title
            binding.copyright.visibility = if (book.copyright == true) View.VISIBLE else View.GONE

            binding.root.setOnClickListener { onClick(book) }
        }
    }

    private class SwipeDiffUtil : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        return SwipeViewHolder(
            ListTileBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    @CallSuper
    override fun submitList(list: List<Book>?) {
        submit(list, false)
    }

    private fun submit(list: List<Book>?, isLocalSubmit: Boolean) {
        if (!isLocalSubmit) {
            removedItems.clear()
        }

        super.submitList(list)
    }

    fun removeItem(position: Int) {
        if (position < itemCount) {
            val item = currentList[position]

            removedItems.add(item)

            val actualList = currentList - removedItems

            if (actualList.isEmpty()) {
                removedItems.clear() // TODO: Should I clear if last item was deleted?
            }

            submit(actualList, true)
        }
    }
}

