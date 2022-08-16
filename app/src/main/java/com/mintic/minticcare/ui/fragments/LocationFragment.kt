package com.mintic.minticcare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mintic.minticcare.R
import com.mintic.minticcare.data.viewmodel.HomeViewModel
import com.mintic.minticcare.databinding.FragmentLocationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentLocationBinding? = null
    private val binding: FragmentLocationBinding get() = _binding!!
    private lateinit var nMap: GoogleMap
    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

  override fun onStart() {
    super.onStart()
    val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.fragment_location_map) as SupportMapFragment
    mapFragment.getMapAsync(this)

  }

  override fun onMapReady(map: GoogleMap) {
    homeViewModel.company()
    nMap = map

    nMap.uiSettings.isScrollGesturesEnabled = true
    nMap.uiSettings.isZoomControlsEnabled = true
    nMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
    nMap.isTrafficEnabled = true
    observeViewModels()
  }

  private fun observeViewModels(){
    homeViewModel.company.observe(viewLifecycleOwner, Observer{
      if(it != null){
        val latLng = LatLng(it.lat, it.lng)
        nMap.addMarker(MarkerOptions().position(latLng).title(it.name))
        nMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
      }

    })
  }
}
