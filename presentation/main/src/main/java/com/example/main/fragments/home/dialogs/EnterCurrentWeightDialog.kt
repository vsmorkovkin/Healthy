package com.example.main.fragments.home.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.main.R
import com.example.main.databinding.DialogEnterCurrentWeightBinding
import com.example.main.extensions.getText
import com.example.main.extensions.showToast

class EnterCurrentWeightDialog : DialogFragment() {

    private var _binding: DialogEnterCurrentWeightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogEnterCurrentWeightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonSaveWeight.setOnClickListener {
                val weight = textInputLayoutCurrentWeight.getText()
                if (weight.isEmpty()) {
                    showToast(getString(R.string.warning_empty_current_weight))
                    return@setOnClickListener
                }

                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY_CURRENT_WEIGHT_ENTERED,
                    bundleOf(BUNDLE_KEY_CURRENT_WEIGHT to weight.toFloat())
                )
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
        const val REQUEST_KEY_CURRENT_WEIGHT_ENTERED = "REQUEST_KEY_CURRENT_WEIGHT_ENTERED"
        const val BUNDLE_KEY_CURRENT_WEIGHT = "BUNDLE_KEY_CURRENT_WEIGHT"
    }
}