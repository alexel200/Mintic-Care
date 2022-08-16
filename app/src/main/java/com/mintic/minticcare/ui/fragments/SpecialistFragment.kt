package com.mintic.minticcare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintic.minticcare.R
import com.mintic.minticcare.data.viewmodel.HomeViewModel
import com.mintic.minticcare.databinding.FragmentSpecialistBinding
import com.mintic.minticcare.ui.adapters.DoctorAdapter
import com.mintic.minticcare.ui.interfaces.OnDoctorClickListener
import com.mintic.minticcare.ui.models.DoctorItemModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpecialistFragment : Fragment() {
    private val args: SpecialistFragmentArgs by navArgs()
    private var _binding: FragmentSpecialistBinding? = null
    private val binding: FragmentSpecialistBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var categories: List<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSpecialistBinding.inflate(inflater, container, false)



        return binding.root
    }

  override fun onStart() {
    super.onStart()
    binding.specialistFragmentRecycler.visibility = View.GONE
    binding.shimmerDoctor.startShimmerAnimation()

    doctorAdapter = DoctorAdapter(listOf())
    if(args.search){
      homeViewModel.doctors(null)
      binding.specialistFragmentTitle.visibility = View.GONE
      binding.specialistFragmentTitle.text = getString(R.string.specialist_fragment_label)
      binding.specialistFragmentSubtitle.text = getString(R.string.specialist_fragment_subtitle)

    }else{
      binding.specialistFragmentTitle.visibility = View.VISIBLE
      binding.specialistFragmentSearchLayout.visibility = View.GONE
      binding.specialistFragmentTitle.text = args.name
      binding.specialistFragmentSubtitle.text = args.description
      homeViewModel.doctors(args.name)
    }

    doctorAdapter.listener = object: OnDoctorClickListener {
      override fun onClick(item: DoctorItemModel) {
        homeViewModel.selected(item)
        findNavController().navigate(R.id.action_specialistFragment_to_speciallistDetailFragment)
      }

    }

    binding.specialistFragmentRecycler.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = doctorAdapter
    }

    observeViewModels()
  }
  private fun observeViewModels(){
    homeViewModel.service.observe(viewLifecycleOwner, Observer{
      if(args.search){
        categories = it.map{ it.name }
        binding.specialistFragmentSearch.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories))
        binding.specialistFragmentSearchLayout.visibility = View.VISIBLE
        binding.specialistFragmentSearch.setOnItemClickListener { parent, view, position, id  ->
          homeViewModel.doctors( categories[position] )
        }
      }
    })

    homeViewModel.doctors.observe(viewLifecycleOwner, Observer {
      binding.shimmerDoctor.stopShimmerAnimation()
      binding.shimmerDoctor.visibility = View.GONE
      binding.specialistFragmentRecycler.visibility = View.VISIBLE

      doctorAdapter.changeDataSet(it)
    })

  }
}
