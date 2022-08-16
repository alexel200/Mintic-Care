package com.mintic.minticcare.data.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintic.minticcare.data.repositories.LoginRepository
import com.mintic.minticcare.ui.models.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val  repo: LoginRepository): ViewModel() {
  private var _login:MutableLiveData<Boolean> = MutableLiveData()
  val login: LiveData<Boolean> get() = _login

  private var _signUp:MutableLiveData<Boolean> = MutableLiveData()
  val signUp: LiveData<Boolean> get() = _signUp

  private var _error:MutableLiveData<String> = MutableLiveData()
  val error: LiveData<String> get() = _error

  private var _user:MutableLiveData<UserModel?> = MutableLiveData()
  val user: LiveData<UserModel?> get() = _user

  private var _forgotPassword: MutableLiveData<Boolean> = MutableLiveData()
  val forgotPassword: LiveData<Boolean> get() = _forgotPassword

  fun login(email: String, password: String){
    viewModelScope.launch {
      try {
        repo.login(email, password)
        _login.postValue(true)
      }catch (e: Exception){
        _error.postValue(e.message)
      }
    }
  }

  fun signup( email: String, password: String, password2:String, name:String, gender:String){
    viewModelScope.launch {
      try {
        repo.signUp(email, password, password2, name, gender)
        _signUp.postValue(true)
      }catch (e: Exception){
        _error.postValue(e.message)
      }
    }
  }

  fun currentUser(){
    viewModelScope.launch {
      _user.postValue(repo.currentUser())
    }
  }

  fun logout(){
    viewModelScope.launch {
      _user.postValue(repo.logOut())
    }
  }

  fun uploadPhoto(image: Bitmap){
    viewModelScope.launch {
      _user.postValue(repo.uploadPhoto(image))
    }
  }

  fun forgotPassword(email: String){
    viewModelScope.launch {
      try {
        repo.passwordRecovery(email)
        _forgotPassword.postValue(true)
      }catch (e: Exception){
        _error.postValue(e.message)
      }
    }
  }

}
