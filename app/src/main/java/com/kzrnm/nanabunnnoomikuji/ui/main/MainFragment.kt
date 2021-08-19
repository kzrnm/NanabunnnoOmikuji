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
import com.kzrnm.nanabunnnoomikuji.BuildConfig
import com.kzrnm.nanabunnnoomikuji.R
import com.kzrnm.nanabunnnoomikuji.databinding.MainFragmentBinding
import com.kzrnm.nanabunnnoomikuji.dialogs.OkCancelDialogFragment

class MainFragment : Fragment(), OkCancelDialogFragment.Listener {

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
        binding.openOmikujiButton.setOnClickListener {
            val uri =
                Uri.parse(BuildConfig.OMIKUJI_URL)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        viewModel.verify()
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun dialogOk(which: Int) {
        viewModel.logout()
    }

    override fun dialogCancel(which: Int) {}

    companion object {
        fun newInstance() = MainFragment()
    }
}