package com.dnieln7.testing.ui.room.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dnieln7.testing.R
import com.dnieln7.testing.TestingExamplesApplication
import com.dnieln7.testing.databinding.FragmentAddCatBinding
import com.dnieln7.testing.model.cats.Cat
import com.dnieln7.testing.viewmodel.CatsViewModel

class AddCatFragment : Fragment() {

    private var _binding: FragmentAddCatBinding? = null
    private val binding get() = _binding!!

    private val catsViewModel by activityViewModels<CatsViewModel> {
        CatsViewModel.Factory((requireActivity().application as TestingExamplesApplication).serviceLocator.database.catDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener {
            if (isFormValid()) {
                val cat = Cat(
                    name = binding.name.text.toString(),
                    description = binding.description.text.toString(),
                    childFriendly = binding.childFriendly.isChecked,
                    dogFriendly = binding.dogFriendly.isChecked,
                    strangerFriendly = binding.strangerFriendly.isChecked
                )

                catsViewModel.save(cat)
                findNavController().popBackStack()
            }
        }
    }

    private fun isFormValid(): Boolean {
        if (binding.name.text.toString().isNotBlank()) {
            binding.name.error = null
        } else {
            binding.nameContainer.error = getString(R.string.wrong_empty)
        }

        return binding.name.error == null
    }
}