package com.kzrnm.nanabunnnoomikuji.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class TextOkCancelDialogFragment : DialogFragment() {
    interface Listener {
        fun dialogOk(text: String, which: Int)
        fun dialogCancel(which: Int)
    }

    private val args: TextOkCancelDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val editText = AppCompatEditText(requireContext()).apply {
            if (args.textHint != null)
                hint = args.textHint
            if (args.defaultText != null)
                setText(args.defaultText)
        }
        val parent = parentFragmentManager.fragments.first() as? Listener
        return AlertDialog.Builder(requireContext())
            .setTitle(args.title)
            .setMessage(args.message)
            .setView(editText)
            .setPositiveButton(args.okButton, parent?.let {
                { _, _ -> it.dialogOk(editText.text.toString(), args.which) }
            })
            .setNegativeButton(args.cancelButton, parent?.let {
                { _, _ -> it.dialogCancel(args.which) }
            })
            .create()
    }
}