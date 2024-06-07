package com.example.main.fragments.activitybyday.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker.
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = String.format("%04d-%02d-%02d", year, month + 1, day)

        parentFragmentManager.setFragmentResult(
            REQUEST_KEY_DATE_SELECTED, bundleOf(
                BUNDLE_KEY_SELECTED_DATE to date
            )
        )
    }

    companion object {
        const val REQUEST_KEY_DATE_SELECTED = "REQUEST_KEY_DATE_SELECTED"
        const val BUNDLE_KEY_SELECTED_DATE = "BUNDLE_KEY_SELECTED_DATE"
    }
}