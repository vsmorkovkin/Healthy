package com.example.main.fragments.home.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.main.R
import com.example.main.databinding.DialogEnterSleepTimeBinding
import com.example.main.extensions.getText
import com.example.main.extensions.showToast
import com.example.main.utils.TimeFormatter.isValidTimeString

class EnterSleepTimeDialog : DialogFragment() {

    private var _binding: DialogEnterSleepTimeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogEnterSleepTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            buttonSaveSleepTime.setOnClickListener {
                val bedtime = textInputLayoutBedtime.getText()
                val wakeupTime = textInputLayoutWakeupTime.getText()

                if (bedtime.isEmpty()
                    || wakeupTime.isEmpty()
                    || !isValidTimeString(bedtime)
                    || !isValidTimeString(wakeupTime)
                ) {
                    showToast(getString(R.string.warning_enter_correct_time))
                    return@setOnClickListener
                }

                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY_SLEEP_TIME_ENTERED, bundleOf(
                        BUNDLE_KEY_BEDTIME to bedtime,
                        BUNDLE_KEY_WAKEUP_TIME to wakeupTime
                    )
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
        const val REQUEST_KEY_SLEEP_TIME_ENTERED = "REQUEST_KEY_SLEEP_TIME_ENTERED"
        const val BUNDLE_KEY_BEDTIME = "BUNDLE_KEY_BEDTIME"
        const val BUNDLE_KEY_WAKEUP_TIME = "BUNDLE_KEY_WAKEUP_TIME"
    }

}