package com.mintic.minticcare.ui.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mintic.minticcare.R
import com.mintic.minticcare.data.viewmodel.LoginViewModel
import com.mintic.minticcare.databinding.FragmentProfileBinding
import com.mintic.minticcare.ui.activities.LoginActivity
import com.mintic.minticcare.ui.utils.CAMERA_PERMISSION_CODE
import com.mintic.minticcare.ui.utils.REQUEST_IMAGE_CODE
import com.mintic.minticcare.ui.utils.checkPermission
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

  override fun onStart() {
    super.onStart()
    loginViewModel.currentUser()
    binding.profileFragmentLogout.setOnClickListener{
      loginViewModel.logout()
    }
    binding.profileFragmentImage.setOnClickListener{
      if(this.checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)){
        openCamera()
      }
    }
    observeViewModels()
  }

  private fun observeViewModels(){
    loginViewModel.user.observe(viewLifecycleOwner, Observer {
      if(it != null){
        binding.profileFragmentEmail.text = it.email
        binding.profileFragmentName.text = it.name
        binding.profileFragmentGender.text = it.gender
        if( it.image == null || it.image == "null" ){
          binding.profileFragmentImage.setImageResource(R.drawable.avatar)
        }else{
          Glide.with(this).load(it.image).into(binding.profileFragmentImage)
        }
      }else{
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
      }

    })

  }
  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    if(requestCode == CAMERA_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
      openCamera()
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if(requestCode == REQUEST_IMAGE_CODE){
      val extras = data?.extras
      if(extras != null ){
        val image = extras["data"] as Bitmap?
        if(image != null) {
          loginViewModel.uploadPhoto(image!!)
        }
        //binding.profileFragmentImage.setImageBitmap(image)
      }

    }
  }

  private fun openCamera(){
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    try {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE)
    }catch (e: ActivityNotFoundException){

    }
  }

}
