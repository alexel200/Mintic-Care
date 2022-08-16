package com.mintic.minticcare.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintic.minticcare.data.repositories.HomeRepository
import com.mintic.minticcare.ui.models.CompanyModel
import com.mintic.minticcare.ui.models.DoctorDetailModel
import com.mintic.minticcare.ui.models.DoctorItemModel
import com.mintic.minticcare.ui.models.ServiceItemModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {

  private var _service: MutableLiveData<List<ServiceItemModel>> = MutableLiveData()
  val service: LiveData<List<ServiceItemModel>> get()  = _service

  private var _doctors: MutableLiveData<List<DoctorItemModel>> = MutableLiveData()
  val doctors: LiveData<List<DoctorItemModel>> get()  = _doctors

  private var _company: MutableLiveData<CompanyModel?> = MutableLiveData()
  val company: LiveData<CompanyModel?> get()  = _company

  private var _selected: MutableLiveData<DoctorItemModel> = MutableLiveData()
  val selected: LiveData<DoctorItemModel> get() = _selected

  private var _doctorDetail: MutableLiveData<DoctorDetailModel?> = MutableLiveData()
  val doctorDetail: LiveData<DoctorDetailModel?> get() = _doctorDetail

  fun services(){
    viewModelScope.launch {
      _service.postValue(repo.services())
    }
  }

  fun doctors(speciality: String?){
    viewModelScope.launch {
      _doctors.postValue(repo.doctors(speciality))
    }
  }

  fun company(){
    viewModelScope.launch {
      _company.postValue(repo.company())
    }
  }

  fun selected(item: DoctorItemModel){
    _selected.postValue(item)
  }

  fun specialistDetail(id: String){
    viewModelScope.launch {
      _doctorDetail.postValue(repo.doctorDetails(id))
    }
  }

  fun clearDetails(){
    _doctorDetail.value = null
  }


}
