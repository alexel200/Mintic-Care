package com.mintic.minticcare.ui.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mintic.minticcare.ui.fragments.*

class OnBoardingAdapter(fm: FragmentManager, lifecycle: Lifecycle, internal var pages:Int): FragmentStatePagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {
  override fun getCount(): Int {
    return pages
  }

  override fun getItem(position: Int): Fragment {
    return when (position) {
      0 -> {
        OnBoardingFragment1()
      }
      1 -> {
        OnBoardingFragment2()
      }
      2 -> {
        OnBoardingFragment3()

      }
      else -> {
        OnBoardingFragment1()
      }
    }
  }


}
