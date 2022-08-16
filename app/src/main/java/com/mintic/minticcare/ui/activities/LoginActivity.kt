package com.mintic.minticcare.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.mintic.minticcare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    private lateinit var binding: ActivityLoginBinding

     override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater )

        setContentView(binding.root)
    }
}




