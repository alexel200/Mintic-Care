package com.mintic.minticcare.data.datasource

import com.mintic.minticcare.R
import com.mintic.minticcare.ui.models.CompanyModel
import com.mintic.minticcare.ui.models.DoctorDetailModel
import com.mintic.minticcare.ui.models.DoctorItemModel
import com.mintic.minticcare.ui.models.ServiceItemModel

class MemoryDataSource {
  fun getDoctors(): List<DoctorItemModel>{
    val specialists = listOf(
        DoctorItemModel("1", "Dra. Maria Castaño", "Especialista", "350+ pacientes", R.drawable.doctor1.toString(), 5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
        DoctorItemModel("2", "Dr. Andrés Calamaro", "Odontologia", "100+ pacientes", R.drawable.doctor2.toString(), 4, ""),
        DoctorItemModel("3", "Dra. Luisa Merlano", "Dermatologia", "350+ pacientes", R.drawable.doctor3.toString(), 3,""),
        DoctorItemModel("4", "Dr. Jorge Ruiz", "Pediatra", "350+ pacientes", R.drawable.doctor4.toString(), 2, ""),
        DoctorItemModel("5", "Dra. Lorena Avila", "General", "350+ pacientes", R.drawable.doctor1.toString(), 1, ""),
        DoctorItemModel("6", "Dr. Rodrigo Aguilar", "General", "350+ pacientes", R.drawable.doctor2.toString(), 2, ""),
        DoctorItemModel("7", "Dra. Maria Bustamante", "Especialista", "350+ pacientes", R.drawable.doctor3.toString(), 3, ""),
        DoctorItemModel("8", "Dra. Valeria Montoya", "Pediatra", "350+ pacientes", R.drawable.doctor4.toString(), 5, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s")
      )
    return specialists
  }

  fun getServices(): List<ServiceItemModel>{
    val services = listOf(
      ServiceItemModel("1", "General", "Los mejores especialistas en medicina general", R.drawable.hospital.toString()),
      ServiceItemModel("2", "Especialista", "Los mejores médicos especialistas", R.drawable.hospital.toString()),
      ServiceItemModel("3", "Odontologia", "Los mejores especialistas en Odontología", R.drawable.hospital.toString()),
      ServiceItemModel("4", "Dermatologia", "Los mejores especialistas en Dermatología", R.drawable.hospital.toString()),
      ServiceItemModel("5", "Pediatria", "Los mejores especialistas en Pediatría", R.drawable.hospital.toString()),
    )
    return services
  }

  fun getCompany():CompanyModel{
    return CompanyModel("1", "MinticCare", 4.6474, -74.1019)
  }

  fun getDoctorDetails(): DoctorDetailModel{
   return DoctorDetailModel("1", "15K+", 4.5, "8yrs+")
  }

}
