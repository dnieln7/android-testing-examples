package com.dnieln7.testing.ui.navigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dnieln7.testing.databinding.FragmentKeyboardBinding
import com.dnieln7.testing.model.keyboard.KeySet
import com.dnieln7.testing.ui.navigation.adapter.KeySetAdapter

class KeyboardFragment : Fragment() {

    private var _binding: FragmentKeyboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeyboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val keySets = listOf(
            KeySet(
                name = "Letters",
                keys = listOf(
                    "A",
                    "B",
                    "C",
                    "D",
                    "E",
                    "F",
                    "G",
                    "H",
                    "I",
                    "J",
                    "K",
                    "L",
                    "M",
                    "N",
                    "O",
                    "P",
                    "Q",
                    "R",
                    "S",
                    "T",
                    "U",
                    "V",
                    "W",
                    "X",
                    "Y",
                    "Z"
                )
            ),
            KeySet(
                name = "Numbers",
                keys = listOf(
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "0"
                )
            ),
            KeySet(
                name = "Symbols",
                keys = listOf(
                    "`",
                    "~",
                    "!",
                    "@",
                    "#",
                    "\$",
                    "%",
                    "^",
                    "&\t",
                    "*",
                    "(",
                    ")",
                    "–",
                    "_",
                    "=",
                    "+",
                    "[",
                    "]",
                    "{",
                    "}",
                    "\\",
                    "|",
                    ";",
                    ":",
                    "‘",
                    "“",
                    ",",
                    ".",
                    "/",
                    "<",
                    ">",
                    "?"
                )
            ),
            KeySet(
                name = "Function",
                keys = listOf(
                    "F1",
                    "F2",
                    "F3",
                    "F4",
                    "F5",
                    "F6",
                    "F7",
                    "F8",
                    "F9",
                    "F10",
                    "F11",
                    "F12"
                )
            )
        )

        binding.keySets.setHasFixedSize(true)
        binding.keySets.adapter = KeySetAdapter(keySets) {
            findNavController().navigate(
                KeyboardFragmentDirections.actionKeyboardFragmentToKeysFragment(it)
            )
        }
    }
}