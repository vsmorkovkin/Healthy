package com.example.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.auth.R
import com.example.auth.databinding.FragmentLoginBinding
import com.example.common.mvi.BaseFragmentMvi
import com.example.common.navigation.NavigationDeeplinkContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment :
    BaseFragmentMvi<LoginPartialState, LoginIntent, LoginState, LoginEffect>(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override val store: LoginStore by viewModels()

    @Inject
    lateinit var deeplinkContainer: NavigationDeeplinkContainer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonLogin.setOnClickListener {
                val email = editTextEmail.text.toString()
                val password = editTextPassword.text.toString()
                store.postIntent(LoginIntent.SignInIntent(email, password))
            }

            buttonNoAccount.setOnClickListener {
                store.postIntent(LoginIntent.NoAccountIntent)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: LoginEffect) {
        when (effect) {
            LoginEffect.SuccessfulLogin -> navigateToMainActivity()

            is LoginEffect.LoginFailure -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }

            LoginEffect.RegisterEffect -> {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }

    override fun render(state: LoginState) {}

    private fun navigateToMainActivity() {
        val intent = Intent(Intent.ACTION_VIEW, deeplinkContainer.DEEPLINK_TO_MAIN_ACTIVITY)
        startActivity(intent)
    }

}