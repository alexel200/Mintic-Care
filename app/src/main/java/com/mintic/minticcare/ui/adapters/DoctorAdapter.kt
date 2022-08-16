package com.mintic.minticcare.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mintic.minticcare.databinding.ItemDoctorBinding
import com.mintic.minticcare.ui.interfaces.OnDoctorClickListener
import com.mintic.minticcare.ui.models.DoctorItemModel

class DoctorAdapter(var list: List<DoctorItemModel>): RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {
  class ViewHolder(val view:ItemDoctorBinding) : RecyclerView.ViewHolder(view.root)

  var listener: OnDoctorClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return ViewHolder(ItemDoctorBinding.inflate(inflater, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = list[position]
    holder.view.itemDoctorName.text = item.name
    holder.view.itemDoctorSpecialist.text = item.speciality
    //holder.view.itemDoctorImage.setImageResource(item.image)
    holder.view.itemDoctorSummary.text = "${item.star} estrellas * ${item.caption}"
    holder.view.itemDoctorStart.rating = (item.star.toFloat()  / 5.0).toFloat()
    Glide.with(holder.view.root).load(item.image).centerCrop().into(holder.view.itemDoctorImage)
    holder.view.root.setOnClickListener{
      listener?.onClick(item)
    }

  }
  fun changeDataSet(list: List<DoctorItemModel>){
    this.list = list
    notifyDataSetChanged()
  }
  override fun getItemCount(): Int {
    return this.list.size
  }


}
