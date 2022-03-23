package com.dnieln7.testing.ui.room.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.FragmentCatDetailBinding
import com.dnieln7.testing.viewmodel.CatsViewModel

class CatDetailFragment : Fragment() {

    private var _binding: FragmentCatDetailBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<CatDetailFragmentArgs>()

    private val catsViewModel by activityViewModels<CatsViewModel> {
        CatsViewModel.Factory((requireActivity().application as TestingExamplesApplication).serviceLocator.database.catDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catsViewModel.getById(navArgs.id).observe(viewLifecycleOwner) {
            binding.name.setText(it.name)
            binding.description.setText(it.description)
            binding.childFriendly.visibility = if (it.childFriendly) View.VISIBLE else View.GONE
            binding.dogFriendly.visibility = if (it.dogFriendly) View.VISIBLE else View.GONE
            binding.strangerFriendly.visibility = if (it.strangerFriendly) View.VISIBLE
            else View.GONE
        }

        binding.edit.setOnClickListener {
            findNavController().navigate(
                CatDetailFragmentDirections.actionCatDetailFragmentToEditCatFragment(navArgs.id)
            )
        }
    }
}