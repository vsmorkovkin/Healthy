package com.example.auth.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.auth.R
import com.example.auth.databinding.FragmentMainBinding
import com.example.session.SessionService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sessionService: SessionService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionService.currentUser.onEach {
            binding.textViewUserUid.text = it?.uid.orEmpty()
        }.launchIn(lifecycleScope)

        binding.buttonLogout.setOnClickListener {
            runCatching {
                lifecycleScope.launch {
                    sessionService.signOut()
                }
            }.onSuccess {
                findNavController().navigate(R.id.action_mainFragment_to_entryFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}