package com.example.auth.fragments.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.auth.R
import com.example.auth.databinding.FragmentRegistrationBinding
import com.example.auth.fragments.registration.model.UserRegistrationUi
import com.example.auth.fragments.registration.mvi.effect.RegistrationEffect
import com.example.auth.fragments.registration.mvi.intent.RegistrationIntent
import com.example.auth.fragments.registration.mvi.state.RegistrationPartialState
import com.example.auth.fragments.registration.mvi.state.RegistrationState
import com.example.auth.fragments.registration.mvi.store.RegistrationStore
import com.example.common.mvi.BaseFragmentMvi
import com.example.common.navigation.NavigationDeeplinkContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RegistrationFragment :
    BaseFragmentMvi<RegistrationPartialState, RegistrationIntent, RegistrationState, RegistrationEffect>(
        R.layout.fragment_registration
    ) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override val store: RegistrationStore by viewModels()

    @Inject
    lateinit var deeplinkContainer: NavigationDeeplinkContainer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fun getUserInfo(): UserRegistrationUi = binding.run {
            val name = editTextName.text.toString()
            val surname = editTextSurname.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val passwordConfirmation = editTextPasswordConfirmation.text.toString()
            UserRegistrationUi(name, surname, email, password, passwordConfirmation)
        }

        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener {
            val userInfo = getUserInfo()
            store.postIntent(RegistrationIntent.RegisterIntent(userInfo))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: RegistrationEffect) {
        fun showToastByStringId(stringId: Int) {
            Toast.makeText(context, getString(stringId), Toast.LENGTH_SHORT).show()
        }

        when (effect) {
            RegistrationEffect.EmptyName -> showToastByStringId(R.string.warning_empty_name)
            RegistrationEffect.EmptySurname -> showToastByStringId(R.string.warning_empty_surname)
            RegistrationEffect.EmptyEmail -> showToastByStringId(R.string.warning_empty_email)
            RegistrationEffect.EmptyPassword -> showToastByStringId(R.string.warning_empty_password)
            RegistrationEffect.EmptyPasswordConfirmation -> showToastByStringId(R.string.warning_empty_password_confirmation)
            RegistrationEffect.NotMatchingPasswords -> showToastByStringId(R.string.warning_not_matching_password)

            RegistrationEffect.SuccessfulRegistration -> navigateToMainActivity()
            is RegistrationEffect.RegistrationFailure -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun render(state: RegistrationState) {}

    private fun navigateToMainActivity() {
        val intent = Intent(Intent.ACTION_VIEW, deeplinkContainer.DEEPLINK_TO_MAIN_ACTIVITY)
        startActivity(intent)
    }

}