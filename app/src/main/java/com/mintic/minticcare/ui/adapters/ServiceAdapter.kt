package com.mintic.minticcare.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mintic.minticcare.databinding.ItemServiceBinding
import com.mintic.minticcare.ui.interfaces.OnServiceClickListener
import com.mintic.minticcare.ui.models.ServiceItemModel

class ServiceAdapter(var list: List<ServiceItemModel>):RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

  var listener: OnServiceClickListener? = null

  class ViewHolder(val view: ItemServiceBinding) : RecyclerView.ViewHolder(view.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return ViewHolder(ItemServiceBinding.inflate(inflater, parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = list[position]
    holder.view.itemServiceTitle.text = item.name
    holder.view.itemServiceDescription.text = item.description
    Glide.with(holder.view.root).load(item.icon)
      .centerCrop().into(holder.view.itemServiceImage)
    //holder.view.itemServiceImage.setImageResource(item.icon)

    holder.view.root.setOnClickListener{
      listener?.onClick(item)
    }

  }

  override fun getItemCount(): Int {
    return list.size
  }

  fun changeDataSet(list: List<ServiceItemModel>){
    this.list = list
    notifyDataSetChanged()
  }


}
