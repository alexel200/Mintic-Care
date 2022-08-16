package com.mintic.minticcare.ui.fragments

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
import com.mintic.minticcare.databinding.FragmentForgotPasswordBinding
import com.mintic.minticcare.ui.utils.isValidEmail
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding: FragmentForgotPasswordBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

      return binding.root
    }

  override fun onStart() {
    super.onStart()
    binding.toLoginButton.setOnClickListener {
      findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginSignupFragment)
    }

    binding.forgotPasswordOkButton.setOnClickListener{
      if(binding.forgotPasswordEmail.text.toString().trim() == ""){
        Snackbar.make(binding.root, "El correo electronico no puede estar vacio", Snackbar.LENGTH_LONG)
      }else if(!binding.forgotPasswordEmail.text.toString().isValidEmail()){
        Snackbar.make(binding.root, "El correo electronico ingresado no es valido", Snackbar.LENGTH_LONG)
      }else{
        loginViewModel.forgotPassword( binding.forgotPasswordEmail.text.toString() )
        observeViewModels()
      }
    }
  }

  private fun observeViewModels(){
    loginViewModel.forgotPassword.observe(viewLifecycleOwner, Observer {
      Snackbar.make(binding.root, "Sí el correo electrónico ingresado es válido, recibira un email en los próximos minutos", Snackbar.LENGTH_LONG).show()
      findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginSignupFragment)
    })

    loginViewModel.error.observe(viewLifecycleOwner, Observer{ m ->
      Snackbar.make(binding.root, m, Snackbar.LENGTH_LONG).show()
    })
  }

}
