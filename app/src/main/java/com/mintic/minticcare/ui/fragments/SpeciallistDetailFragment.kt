package com.mintic.minticcare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.mintic.minticcare.data.viewmodel.HomeViewModel
import com.mintic.minticcare.databinding.FragmentSpeciallistDetailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpeciallistDetailFragment : Fragment() {
    private var _binding: FragmentSpeciallistDetailBinding? = null
    private val binding: FragmentSpeciallistDetailBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpeciallistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

  override fun onStart() {
    super.onStart()
    observeViewModels()
  }

  override fun onStop() {
    super.onStop()
    homeViewModel.clearDetails()
  }

  private fun observeViewModels(){
    homeViewModel.selected.observe(viewLifecycleOwner, Observer{
      homeViewModel.specialistDetail(it.id)

      //binding.specialistFragmentImage.setImageResource(it.image)
      Glide.with(binding.root).load(it.image).centerCrop().into(binding.specialistFragmentImage)
      binding.specialistFragmentDetailName.text = it.name
      binding.specialistFragmentDetailSpeciallity.text = it.speciality
      binding.specialistFragmentDetailRating.rating = it.star.toFloat()
      binding.specialistFragmentDescription.text = it.description
    })

    homeViewModel.doctorDetail.observe(viewLifecycleOwner, Observer {
      if(it != null) {
        binding.doctorDetailFragmentCardPatientsName.text = it.patients
        binding.doctorDetailFragmentCardGradeName.text = it.star.toString()
        binding.specialistFragmentDetailRating.rating = (it.star / 5.0).toFloat()
        binding.doctorDetailFragmentCardExpName.text = it.exp
      }
    })
  }
}
