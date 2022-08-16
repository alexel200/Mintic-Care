package com.mintic.minticcare.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.mintic.minticcare.R
import com.mintic.minticcare.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.homeToolbar)
    }

  override fun onStart() {
    super.onStart()
    val navController = findNavController(R.id.nav_host_home_fragment)
    val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.locationFragment, R.id.specialistFragment, R.id.profileFragment))
    binding.homeNavigation.setupWithNavController(navController)
    binding.homeToolbar.setupWithNavController(navController, appBarConfiguration)

  }
}
