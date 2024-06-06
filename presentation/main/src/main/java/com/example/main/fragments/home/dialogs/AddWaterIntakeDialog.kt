package com.example.main.fragments.home.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.main.R
import com.example.main.databinding.DialogAddWaterIntakeBinding
import com.example.main.extensions.getText
import com.example.main.extensions.showToast

class AddWaterIntakeDialog : DialogFragment() {

    private var _binding: DialogAddWaterIntakeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddWaterIntakeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonAddWaterIntake.setOnClickListener {
                val waterIntake = textInputLayoutWaterIntake.getText()
                if (waterIntake.isEmpty()) {
                    showToast(getString(R.string.warning_empty_water_intake))
                    return@setOnClickListener
                }

                parentFragmentManager.setFragmentResult(REQUEST_KEY_WATER_INTAKE_ENTERED, bundleOf(
                    BUNDLE_KEY_WATER_INTAKE to waterIntake.toInt()
                ))
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.run {
            setBackgroundDrawableResource(R.drawable.bg_dialog)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val REQUEST_KEY_WATER_INTAKE_ENTERED = "REQUEST_KEY_WATER_INTAKE_ENTERED"
        const val BUNDLE_KEY_WATER_INTAKE = "BUNDLE_KEY_WATER_INTAKE"
    }

}