package com.mintic.minticcare

import android.app.Application
import com.mintic.minticcare.di.dataSourceModule
import com.mintic.minticcare.di.repoModule
import com.mintic.minticcare.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MinticCare: Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin{
      androidLogger()
      androidContext(this@MinticCare)
      modules(listOf(dataSourceModule, repoModule, viewModelModule))
    }
  }
}
