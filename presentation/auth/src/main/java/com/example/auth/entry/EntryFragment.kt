package com.example.auth.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.auth.R
import com.example.auth.databinding.FragmentEntryBinding


class EntryFragment : Fragment() {
    
    private var _binding: FragmentEntryBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonLoginEntry.setOnClickListener {
                findNavController().navigate(R.id.action_entryFragment_to_loginFragment)
            }
        }

    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}