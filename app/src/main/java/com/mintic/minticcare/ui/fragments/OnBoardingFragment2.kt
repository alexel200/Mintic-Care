package com.mintic.minticcare.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mintic.minticcare.databinding.FragmentOnBoarding2Binding
import com.mintic.minticcare.ui.activities.LoginActivity

class OnBoardingFragment2: Fragment() {
  private var _binding: FragmentOnBoarding2Binding? = null
  private val binding: FragmentOnBoarding2Binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentOnBoarding2Binding.inflate(inflater, container, false)

    binding.onBoardSkip.setOnClickListener {
      val intent = Intent(context, LoginActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      startActivity(intent)
      requireActivity().finish()
    }


    return binding.root
  }

  override fun onStart() {
    super.onStart()
    requireActivity().window.statusBarColor = android.graphics.Color.parseColor("#FE619E")
  }

  override fun onResume() {
    super.onResume()
    requireActivity().window.statusBarColor = android.graphics.Color.parseColor("#FE619E")


  }

}
