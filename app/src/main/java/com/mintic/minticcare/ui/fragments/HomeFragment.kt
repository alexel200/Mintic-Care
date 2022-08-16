package com.mintic.minticcare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mintic.minticcare.data.viewmodel.HomeViewModel
import com.mintic.minticcare.databinding.FragmentHomeBinding
import com.mintic.minticcare.ui.adapters.ServiceAdapter
import com.mintic.minticcare.ui.interfaces.OnServiceClickListener
import com.mintic.minticcare.ui.models.ServiceItemModel
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private lateinit var serviceAdapter: ServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

  override fun onStart() {
    super.onStart()

    //Shimmer animation
    binding.homeFragmentRecycler.visibility = View.GONE

    binding.shimmerServices.startShimmerAnimation()

    homeViewModel.services()

    serviceAdapter = ServiceAdapter(listOf())

    serviceAdapter.listener = object: OnServiceClickListener{
      override fun onClick(item: ServiceItemModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToSpecialistFragment()

            action.name = item.name
            action.description = item.description
            action.search = false
            findNavController().navigate(action)
      }
    }


    binding.homeFragmentRecycler.apply {
      adapter = serviceAdapter
      layoutManager = LinearLayoutManager(requireContext())
      binding.homeFragmentRecycler.itemAnimator = SlideInLeftAnimator()

    }




    observeViewModels()

  }

  private fun observeViewModels(){
    homeViewModel.service.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
      serviceAdapter.changeDataSet(it)
      binding.shimmerServices.stopShimmerAnimation()
      binding.shimmerServices.visibility = View.GONE
      binding.homeFragmentRecycler.visibility = View.VISIBLE
    })
  }

}
