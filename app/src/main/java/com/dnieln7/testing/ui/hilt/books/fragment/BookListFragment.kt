package com.dnieln7.testing.ui.hilt.books.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dnieln7.testing.R
import com.dnieln7.testing.databinding.FragmentBookListBinding
import com.dnieln7.testing.model.book.Book
import com.dnieln7.testing.ui.hilt.adapter.BookAdapter
import com.dnieln7.testing.utils.DataSource
import com.dnieln7.testing.utils.snackShort
import com.dnieln7.testing.viewmodel.BookViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private val bookViewModel by activityViewModels<BookViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookViewModel.dataResponse.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = false

            binding.books.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE

            if (it.data != null) {
                binding.books.adapter = BookAdapter(it.data) { b -> goToDetail(b) }
            }

            if (it.source == DataSource.DATABASE) {
                binding.headerMessage.title.text = getString(R.string.using_local_data)

                lifecycleScope.launch {
                    binding.headerMessage.root.visibility = View.VISIBLE

                    delay(3000)

                    binding.headerMessage.root.visibility = View.GONE
                }
            }

            if (it.error != null) {
                binding.root.snackShort("${it.error}")
            }
        }

        binding.books.setHasFixedSize(true)
        binding.refresh.setOnRefreshListener { bookViewModel.get() }
    }

    private fun goToDetail(book: Book) {
        findNavController().navigate(
            BookListFragmentDirections.actionBookListFragmentToBookDetailFragment(book)
        )
    }
}