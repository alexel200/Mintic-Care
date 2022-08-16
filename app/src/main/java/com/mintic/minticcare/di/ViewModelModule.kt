package com.mintic.minticcare.di


import com.mintic.minticcare.data.viewmodel.HomeViewModel
import com.mintic.minticcare.data.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
  viewModel{
    LoginViewModel(get())
  }
  viewModel{
    HomeViewModel(get())
  }
}
