package com.example.main.fragments.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.common.mvi.BaseFragmentMvi
import com.example.common.navigation.NavigationDeeplinkContainer
import com.example.main.R
import com.example.main.databinding.FragmentProfileBinding
import com.example.main.fragments.profile.mvi.effect.ProfileEffect
import com.example.main.fragments.profile.mvi.intent.ProfileIntent
import com.example.main.fragments.profile.mvi.state.ProfilePartialState
import com.example.main.fragments.profile.mvi.state.ProfileState
import com.example.main.fragments.profile.mvi.store.ProfileStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment :
    BaseFragmentMvi<ProfilePartialState, ProfileIntent, ProfileState, ProfileEffect>(
        R.layout.fragment_profile
    ) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override val store: ProfileStore by viewModels()

    @Inject
    lateinit var deeplinkContainer: NavigationDeeplinkContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        store.postIntent(ProfileIntent.GetUserProfile)

        binding.run {
            imageViewProfile.setOnClickListener {
                store.postIntent(ProfileIntent.SelectUserProfileImageFromDevice)
            }

            buttonLogout.setOnClickListener {
                store.postIntent(ProfileIntent.PerformLogout)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun resolveEffect(effect: ProfileEffect) {
        fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        when (effect) {
            ProfileEffect.SuccessfulLogout -> navigateToAuthActivity()
            ProfileEffect.OpenSelectImageDialog -> openSelectImageDialog()
            ProfileEffect.UserProfileImageChanged -> parentFragmentManager.setFragmentResult(REQUEST_KEY_CHANGED_PROFILE_IMAGE, bundleOf())
            is ProfileEffect.SetUserProfileImageFailure -> showToast("Не удалось изменить изображение профиля. Ошибка: ${effect.message}")
            is ProfileEffect.GetUserProfileFailure -> showToast("Не удалось получить данные профиля. Ошибка: ${effect.message}")
            is ProfileEffect.LogoutFailure -> showToast("Не удалось выполнить выход. Ошибка: ${effect.message}")
        }
    }

    override fun render(state: ProfileState) {
        state.userProfileUi?.let { userProfileUi ->
            binding.run {
                textViewUserNameAndSurname.text = userProfileUi.nameAndSurname

                userProfileUi.imageUrl?.let {
                    Glide.with(imageViewProfile.context)
                        .load(userProfileUi.imageUrl)
                        .centerCrop()
                        .into(imageViewProfile)
                }
            }
        }
    }

    private fun navigateToAuthActivity() {
        val intent = Intent(Intent.ACTION_VIEW, deeplinkContainer.DEEPLINK_TO_AUTH_ACTIVITY)
        startActivity(intent)
    }

    private fun openSelectImageDialog() {
        pickImage.launch(Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        })
    }

    private val pickImage =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.data
                data?.let { uri ->
                    store.postIntent(ProfileIntent.SetUserProfileImage(uri.toString()))
                }
            }
        }

    companion object {
        const val REQUEST_KEY_CHANGED_PROFILE_IMAGE = "REQUEST_KEY_CHANGED_PROFILE_IMAGE"
    }
}