package com.mintic.minticcare.ui.interfaces

import com.mintic.minticcare.ui.models.ServiceItemModel

interface OnServiceClickListener {
  fun onClick(item: ServiceItemModel)
}
