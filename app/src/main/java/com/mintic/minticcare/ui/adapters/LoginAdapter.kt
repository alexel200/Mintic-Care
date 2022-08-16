package com.mintic.minticcare.ui.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mintic.minticcare.ui.fragments.LoginTabFragment
import com.mintic.minticcare.ui.fragments.SignupTabFragment


class LoginAdapter( fm: FragmentManager, lifecycle: Lifecycle, internal var totalTabs:Int ):FragmentStateAdapter(fm, lifecycle) {
  override fun getItemCount(): Int {
    Log.d("Total Tabs", totalTabs.toString())
    return totalTabs
  }

  override fun createFragment(position: Int): Fragment {
    Log.d("Position initial => ", position.toString())
    return when(position){
      0 -> {
        Log.d("Position => ", position.toString())
        LoginTabFragment()
      }
      1 ->{
        Log.d("Position => ", position.toString())
        SignupTabFragment()
      }
      else -> {
        Log.d("Position else => ", position.toString())
        LoginTabFragment()
      }
    }



  }

}
