package com.dnieln7.testing.ui.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dnieln7.testing.databinding.FragmentKeysBinding
import com.dnieln7.testing.ui.navigation.adapter.KeyAdapter
import com.dnieln7.testing.ui.navigation.adapter.KeySetAdapter

class KeysFragment : Fragment() {

    private var _binding: FragmentKeysBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<KeysFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.keys.setHasFixedSize(true)
        binding.keys.adapter = KeyAdapter(navArgs.keySet.keys)
    }
}