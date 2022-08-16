package com.mintic.minticcare.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.mintic.minticcare.data.datasource.MemoryDataSource
import com.mintic.minticcare.ui.models.CompanyModel
import com.mintic.minticcare.ui.models.DoctorDetailModel
import com.mintic.minticcare.ui.models.DoctorItemModel
import com.mintic.minticcare.ui.models.ServiceItemModel
import com.mintic.minticcare.ui.utils.COMPANY_COLLECTION
import com.mintic.minticcare.ui.utils.DETAIL_COLLECTION
import com.mintic.minticcare.ui.utils.DOCTOR_COLLECTION
import com.mintic.minticcare.ui.utils.SERVICE_COLLECTION
import kotlinx.coroutines.tasks.await

class HomeRepository(private val memoryDataSource: MemoryDataSource,
private val firestoreService: FirebaseFirestore, private val storageService: FirebaseStorage) {
  suspend fun services(): List<ServiceItemModel>{
    //delay(2000)
    //return memoryDataSource.getServices()
    val result = ( firestoreService.collection(SERVICE_COLLECTION).orderBy("id").get().await() ).toObjects<ServiceItemModel>()
    val transformed = result.map{
      val ref = storageService.reference
      val imgRef = ref.child(it.icon)
      it.icon = (imgRef.downloadUrl.await()).toString()
      return@map it
    }
    return transformed
  }

  suspend fun doctors(speciality: String? ): List<DoctorItemModel>{
      /*var specialists = memoryDataSource.getDoctors()
      if(speciality != null){
        specialists = specialists.filter{ it.speciality == speciality }
      }
      return specialists*/

    val result:List<DoctorItemModel>
    if(speciality == null){
      result = (firestoreService.collection(DOCTOR_COLLECTION).get().await() ).toObjects<DoctorItemModel>()
    }else{
      result =  (firestoreService.collection(DOCTOR_COLLECTION).whereEqualTo("speciality", speciality).get().await()).toObjects()
    }
    val transformed = result.map {
      val ref =  storageService.reference
      val imgRef = ref.child(it.image)
      it.image = (imgRef.downloadUrl.await()).toString()
      return@map it
    }
    return transformed
  }
  suspend fun company():CompanyModel?{
    val result = firestoreService.collection(COMPANY_COLLECTION).get().await()
    return result.documents.first().toObject<CompanyModel?>()
  }

  suspend fun doctorDetails(id: String): DoctorDetailModel?{
    /*delay(5000)
    return memoryDataSource.getDoctorDetails()*/

    return (firestoreService.collection(DETAIL_COLLECTION).document(id).get().await()).toObject<DoctorDetailModel?>()

  }
}
