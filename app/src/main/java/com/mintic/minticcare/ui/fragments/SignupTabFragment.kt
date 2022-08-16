package com.mintic.minticcare.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.mintic.minticcare.R
import com.mintic.minticcare.data.viewmodel.LoginViewModel
import com.mintic.minticcare.databinding.FragmentSignupTabFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignupTabFragment: Fragment() {

  private var _binding: FragmentSignupTabFragmentBinding? = null
  private val binding: FragmentSignupTabFragmentBinding get() = _binding!!

  private val loginViewModel:LoginViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentSignupTabFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }


  override fun onStart() {
    super.onStart()
    Log.d("Error:", "Ingresa signup")
    binding.signupFragmentButton.setOnClickListener {
      val id = binding.signupRadioGroup.checkedRadioButtonId
      val raddioButton = requireActivity().findViewById(id) as RadioButton

      loginViewModel.signup(
        binding.signupEmail.text.toString(),
        binding.signupPassword.text.toString(),
        binding.signupPassword2.text.toString(),
        binding.signupFullname.text.toString(),
        raddioButton.text.toString()
      )
    }
    observeViewModels()
  }

  private fun observeViewModels(){
    loginViewModel.signUp.observe(viewLifecycleOwner, Observer {
      Snackbar.make(binding.root, "Registro Exitoso", Snackbar.LENGTH_LONG).show()
      try {
        binding.signupFullname.setText("")
        binding.signupEmail.setText("")
        binding.signupPassword.setText("")
        binding.signupPassword2.setText("")
        binding.signupFragmentButton.isEnabled = false
        (parentFragment!!.activity!!.findViewById<View>(R.id.tabLayout) as TabLayout).getTabAt(0)!!.select()
        binding.signupFragmentButton.isEnabled = true
      }catch(e:Exception ){
        Log.d("Error", e.message.toString())
        Snackbar.make(binding.root, e.message.toString(), Snackbar.LENGTH_LONG).show()
      }
    })

    loginViewModel.error.observe(viewLifecycleOwner, Observer {
      Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
    })
  }

}
