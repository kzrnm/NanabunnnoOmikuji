package com.kzrnm.nanabunnnoomikuji.ui.login

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
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.kzrnm.nanabunnnoomikuji.R
import com.kzrnm.nanabunnnoomikuji.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.pinOk.setOnClickListener {
            if (viewModel.pin.value?.isEmpty() != false) {
                return@setOnClickListener
            }
            viewModel.auth()
        }

        viewModel.authResult.observe(viewLifecycleOwner, { info ->
            if (info == null) {
                Toast.makeText(requireContext(), R.string.failed_to_login, Toast.LENGTH_LONG).show()
            }
            val nav = this@LoginFragment.findNavController()
            val currentDestination = nav.currentDestination as? FragmentNavigator.Destination
            if (currentDestination != null && currentDestination.className == this.javaClass.name) {
                nav.popBackStack()
            }
        })

        viewModel.getAuthorizationURL()
        viewModel.authorizationURL.observe(viewLifecycleOwner, { authorizationURL ->
            if (authorizationURL == null) return@observe
            val uri = Uri.parse(authorizationURL)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        })
    }

}