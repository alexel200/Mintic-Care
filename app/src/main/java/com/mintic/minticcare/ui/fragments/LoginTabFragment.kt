package com.mintic.minticcare.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mintic.minticcare.R
import com.mintic.minticcare.data.viewmodel.LoginViewModel
import com.mintic.minticcare.databinding.FragmentLoginTapFragmentBinding
import com.mintic.minticcare.ui.activities.HomeActivity
import com.mintic.minticcare.ui.utils.isValidEmail
import com.mintic.minticcare.ui.utils.isValidPassword
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginTabFragment: Fragment() {
  private var _binding: FragmentLoginTapFragmentBinding? = null
  private val binding: FragmentLoginTapFragmentBinding get() = _binding!!

  private val loginViewModel: LoginViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentLoginTapFragmentBinding.inflate(inflater, container, false)

    return binding.root
  }


  override fun onStart() {
    super.onStart()
    binding.loginForgotPassword.setOnClickListener {
      findNavController().navigate(R.id.action_loginSignupFragment_to_forgotPasswordFragment)
    }

    binding.loginButton.setOnClickListener {
      if(!binding.loginUsername.text.toString().isValidEmail()){
        binding.loginUsernameLayout.error = "Correo Electrónico invalido"
      }else{
        binding.loginUsernameLayout.error = null
      }

      if(!binding.password.text.toString().isValidPassword()){
        binding.passwordLayout.error = "La contraseña debe contener al menos 6 caracteres"
      }else{
        binding.passwordLayout.error = null
      }

      if(binding.loginUsername.text.toString().isValidEmail() && binding.password.text.toString().isValidPassword() ) {
        loginViewModel.login(binding.loginUsername.text.toString(), binding.password.text.toString())
      }

    }
    observeViewModels()
  }

  private fun observeViewModels(){
    loginViewModel.login.observe(viewLifecycleOwner, Observer {
      val intent = Intent(context, HomeActivity::class.java)
      startActivity(intent)
      requireActivity().finish()
    })

    loginViewModel.error.observe(viewLifecycleOwner, Observer{ m ->
      Snackbar.make(binding.root, m, Snackbar.LENGTH_LONG).show()
    })
  }


}
