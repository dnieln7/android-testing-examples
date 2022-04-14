package com.dnieln7.testing.ui.hilt.books.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dnieln7.testing.databinding.FragmentBookDetailBinding
import com.dnieln7.testing.databinding.TextViewGenericBinding

class BookDetailFragment : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<BookDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = navArgs.book.title

        navArgs.book.authors.forEach {
            val layout = TextViewGenericBinding.inflate(layoutInflater)

            layout.root.text = it.name

            binding.authors.addView(layout.root)
        }

        navArgs.book.languages.forEach {
            val layout = TextViewGenericBinding.inflate(layoutInflater)

            layout.root.text = it

            binding.languages.addView(layout.root)
        }

        navArgs.book.subjects.forEach {
            val layout = TextViewGenericBinding.inflate(layoutInflater)

            layout.root.text = it

            binding.subjects.addView(layout.root)
        }

        navArgs.book.bookshelves.forEach {
            val layout = TextViewGenericBinding.inflate(layoutInflater)

            layout.root.text = it

            binding.bookshelves.addView(layout.root)
        }

        binding.returnToCollection.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}