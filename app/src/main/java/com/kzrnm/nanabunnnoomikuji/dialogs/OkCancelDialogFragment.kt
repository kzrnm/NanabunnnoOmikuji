package com.kzrnm.nanabunnnoomikuji.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class OkCancelDialogFragment : DialogFragment() {
    interface Listener {
        fun dialogOk(which: Int)
        fun dialogCancel(which: Int)
    }

    private val args: OkCancelDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val parent = parentFragmentManager.fragments.first() as? Listener
        return AlertDialog.Builder(requireContext())
            .setTitle(args.title)
            .setMessage(args.message)
            .setPositiveButton(args.okButton, parent?.let {
                { _, _ -> it.dialogOk(args.which) }
            })
            .setNegativeButton(args.cancelButton, parent?.let {
                { _, _ -> it.dialogCancel(args.which) }
            })
            .create()
    }
}