package com.mintic.minticcare.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mintic.minticcare.data.datasource.MemoryDataSource
import org.koin.dsl.module

val dataSourceModule = module{
  single{
    MemoryDataSource()
  }

  single{
    Firebase.auth
  }

  single{
    Firebase.firestore
  }
  single{
    Firebase.storage("gs://minticcare2022.appspot.com")
  }
}
