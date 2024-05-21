package com.example.auth.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.auth.databinding.FragmentRegistrationBinding
import com.example.auth.registration.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

            buttonRegister.setOnClickListener {
                val email = editTextLogin.text.toString()
                val password = editTextPassword.text.toString()
                val passwordConfirmation = editTextPasswordConfirmation.text.toString()

                if (password != passwordConfirmation) {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.register(email, password)
            }

        }

        viewModel.isRegistered.onEach {
            val message = if (it) {
                "Пользователь успешно зарегистрирован"
            } else {
                "Не удалось зарегистровать пользователя"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)

    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}