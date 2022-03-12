package com.dnieln7.testing.ui.cats.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.FragmentCatsBinding
import com.dnieln7.testing.ui.cats.adapter.CatAdapter
import com.dnieln7.testing.ui.navigation.adapter.KeyAdapter
import com.dnieln7.testing.viewmodel.CatsViewModel

class CatsFragment : Fragment() {

    private var _binding: FragmentCatsBinding? = null
    private val binding get() = _binding!!

    private val catsViewModel by activityViewModels<CatsViewModel> {
        CatsViewModel.Factory((requireActivity().application as TestingExamplesApplication).serviceLocator.database.catDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cats.setHasFixedSize(true)
        binding.add.setOnClickListener {
            findNavController().navigate(CatsFragmentDirections.actionCatsFragmentToAddCatFragment())
        }

        catsViewModel.cats.observe(viewLifecycleOwner) { cats ->
            binding.cats.adapter = CatAdapter(
                data = cats,
                onClick = {toDetail(it)},
                onDelete = { catsViewModel.delete(it) }
            )
        }
    }

    private fun toDetail(id: Int) {
        findNavController().navigate(CatsFragmentDirections.actionCatsFragmentToCatDetailFragment(id))
    }
}