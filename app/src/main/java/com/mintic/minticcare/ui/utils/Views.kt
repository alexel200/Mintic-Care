package com.mintic.minticcare.ui.utils

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

fun Fragment.checkPermission(permission: String, code: Int): Boolean{
  if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
    val granted =  requireActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    if(!granted) this.requestPermissions(arrayOf(permission), code)
    return granted
  }
  return true
}
