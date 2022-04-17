package com.dnieln7.testing.ui.swipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.dnieln7.testing.R
import com.dnieln7.testing.databinding.ActivitySwipeBinding
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.repository.book.FakeBookRepository
import com.dnieln7.testing.ui.swipe.adapter.SwipeAdapter
import com.dnieln7.testing.utils.SwipeToDelete
import com.dnieln7.testing.utils.snackShort
import com.dnieln7.testing.utils.toastLong
import com.dnieln7.testing.viewmodel.SwipeViewModel

class SwipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySwipeBinding
    private lateinit var adapter: SwipeAdapter

    private val swipeViewModel by viewModels<SwipeViewModel> {
        SwipeViewModel.Factory(FakeBookRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SwipeAdapter {
            binding.root.snackShort("${it.id}")
        }

        swipeViewModel.dataResponse.observe(this) {
            binding.refresh.isRefreshing = false

            if (it.data != null) {
                adapter.submitList(it.data)
            }

            if (it.error != null) {
                toastLong(it.error)
            }
        }

        swipeViewModel.deleteState.observe(this) {
            if (it.success) {

                adapter.removeItem(it.deletedPos)
                binding.root.snackShort(getString(R.string.book_deleted))
            }

            if (it.error != null) {
                toastLong(it.error)
            }
        }

        binding.books.setHasFixedSize(true)
        binding.books.adapter = adapter

        ItemTouchHelper(SwipeToDelete {
            val toDelete = adapter.currentList[it]

            swipeViewModel.delete(it, toDelete)
        }).attachToRecyclerView(binding.books)

        binding.refresh.setOnRefreshListener { swipeViewModel.get() }
    }
}