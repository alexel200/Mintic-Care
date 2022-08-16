package com.mintic.minticcare.ui.activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider
import com.mintic.minticcare.data.viewmodel.LoginViewModel
import com.mintic.minticcare.databinding.ActivitySplashBinding
import com.mintic.minticcare.ui.adapters.OnBoardingAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val loginViewModel:LoginViewModel by viewModel()
    private val NUMBER_PAGES = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        binding.liquidPage.visibility = View.GONE
        setContentView(binding.root)
    }

  override fun onStart() {
    super.onStart()
    binding.splashAnimation.playAnimation()

    binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
      override fun onAnimationStart(p0: Animator?) {}
      override fun onAnimationCancel(p0: Animator?) {}
      override fun onAnimationRepeat(p0: Animator?) {}
      override fun onAnimationEnd(p0: Animator?) {
        loginViewModel.currentUser()
      }
    })
    observeViewModels()
  }

  private fun observeViewModels(){
    loginViewModel.user.observe(this, Observer{
      if(it == null){
        Log.d("ENtra", " => Log sin usuario")
        binding.liquidPage.elevation = 40F

        binding.liquidPage.visibility = View.VISIBLE

        var titleArray = arrayOf("Uno", "Dos", "Tres")
        val liquidSwipeClipPathProviders = Array(titleArray.count()) {
          LiquidSwipeClipPathProvider()
        }
        var viewPager = binding.liquidPage

        viewPager.adapter = OnBoardingAdapter(supportFragmentManager, lifecycle,NUMBER_PAGES)
        viewPager.setOnTouchListener { _, event ->
          val waveCenterY = event.y
          liquidSwipeClipPathProviders.map {
            it.waveCenterY = waveCenterY
          }
          false
        }

      }else{
        val intent = Intent(applicationContext, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
      }
    })
  }

}
