package com.kzrnm.nanabunnnoomikuji.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kzrnm.nanabunnnoomikuji.R
import com.kzrnm.nanabunnnoomikuji.databinding.MainFragmentBinding
import com.kzrnm.nanabunnnoomikuji.dialogs.OkCancelDialogFragment
import com.kzrnm.nanabunnnoomikuji.dialogs.TextOkCancelDialogFragment

class MainFragment : Fragment(), OkCancelDialogFragment.Listener,
    TextOkCancelDialogFragment.Listener {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.licenseButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToLicenseActivity()
            findNavController().navigate(action)
        }
        binding.loginLogoutButton.setOnClickListener {
            val action = if (viewModel.isLoggedIn.value == true) {
                MainFragmentDirections.actionMainFragmentToOkCancelDialogFragment(
                    "ログアウト",
                    "ログアウトしますか？",
                    "ログアウトする",
                    "キャンセル"
                )
            } else {
                MainFragmentDirections.actionMainFragmentToLoginFragment()
            }
            findNavController().navigate(action)
        }
        binding.openOmikujiButton.setOnLongClickListener {
            val action = MainFragmentDirections.actionMainFragmentToTextOkCancelDialogFragment(
                "URL",
                "開くURLを設定",
                "OK",
                "Cancel",
                viewModel.pref.openUrl,
                "http://example.com/example",
                which = 1
            )
            findNavController().navigate(action)
            true
        }
        binding.openOmikujiButton.setOnClickListener {
            try {
                val uri =
                    Uri.parse(viewModel.pref.openUrl)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } catch (e: Exception) {

            }
        }

        viewModel.verify()
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun dialogOk(text: String, which: Int) {
        viewModel.pref.openUrl = text
    }

    override fun dialogOk(which: Int) {
        viewModel.logout()
    }

    override fun dialogCancel(which: Int) {}

    companion object {
        fun newInstance() = MainFragment()
    }
}