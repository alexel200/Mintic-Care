package com.mintic.minticcare.ui.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.fragment.app.Fragment
import com.google.type.Color
import com.mintic.minticcare.databinding.FragmentOnBoarding1Binding
import com.mintic.minticcare.ui.activities.LoginActivity

class OnBoardingFragment1: Fragment() {
  private var _binding: FragmentOnBoarding1Binding? = null
  private val binding: FragmentOnBoarding1Binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentOnBoarding1Binding.inflate(inflater, container, false)

    binding.onBoardSkip.setOnClickListener {
      val intent = Intent(context, LoginActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      startActivity(intent)
      requireActivity().finish()
    }
    //requireActivity().window.statusBarColor = android.graphics.Color.parseColor("#009BDE")


    return binding.root
  }

  override fun onStart() {
    super.onStart()

    requireActivity().window.statusBarColor = android.graphics.Color.parseColor("#ffffff")

  }

  override fun onResume() {
    super.onResume()
    requireActivity().window.statusBarColor = android.graphics.Color.parseColor("#ffffff")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
      requireActivity().window.insetsController?.setSystemBarsAppearance(
        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
      )
    }
  }

}
