package com.mintic.minticcare.di

import com.mintic.minticcare.data.repositories.HomeRepository
import com.mintic.minticcare.data.repositories.LoginRepository

import org.koin.dsl.module

val repoModule = module{
  single{ LoginRepository(get(), get(), get()) }
  single { HomeRepository(get(), get(), get()) }
}
