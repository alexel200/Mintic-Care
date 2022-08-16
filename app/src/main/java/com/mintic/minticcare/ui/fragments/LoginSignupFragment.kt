package com.mintic.minticcare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mintic.minticcare.R
import com.mintic.minticcare.databinding.FragmentLoginSignupFragmentBinding
import com.mintic.minticcare.ui.adapters.LoginAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [LoginSignupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginSignupFragment : Fragment() {

    private var _binding: FragmentLoginSignupFragmentBinding? = null
    private val binding: FragmentLoginSignupFragmentBinding get() = _binding!!

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    _binding = FragmentLoginSignupFragmentBinding.inflate(layoutInflater)

    tabLayout = binding.tabLayout
    viewPager = binding.viewPager2

    tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    tabLayout.addTab(tabLayout.newTab().setText("Login"))
    tabLayout.addTab(tabLayout.newTab().setText("Signup"))

    viewPager.adapter = LoginAdapter(childFragmentManager, lifecycle, tabLayout.tabCount)

    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
      when (position) {
        0 -> { tab.text = "Login"}
        1 -> { tab.text = "Signup"}
      }

    }.attach()


  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {


    val root =  inflater.inflate(R.layout.fragment_login_signup_fragment, container, false)

    viewPager = binding.viewPager2
    viewPager.adapter = LoginAdapter(childFragmentManager, lifecycle, tabLayout.tabCount)

    return binding.root
  }


    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ENTRA", "ENTRA AL VIEW")
        // Inflate the layout for this fragment
        // _binding = FragmentLoginSignupFragmentBinding.inflate(inflater, container, false)

        //var view: View = inflater.inflate(R.layout.fragment_login_signup_fragment, container, false)

       // view.setContentView(binding.root)
        tabLayout = binding.lsTabLayout
        viewPager = binding.lsViewPager2

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        var adapter = LoginAdapter(parentFragmentManager, lifecycle, tabLayout.tabCount)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
          when (position) {
            0 -> { tab.text = "Login"}
            1 -> { tab.text = "Signup"}
          }

        }.attach()


        return binding.root
    }*/

}
