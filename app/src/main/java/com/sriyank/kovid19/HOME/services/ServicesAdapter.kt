package com.sriyank.kovid19.HOME.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.kovid19.R

class servicesAdapter (var myList : ArrayList <ServicesData>) :
    RecyclerView.Adapter<servicesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.services_items,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var services = myList[position]

        holder.img.setImageResource(services.Image)
        holder.title.text = services.Title
        holder.description.text = services.Description

    }

    override fun getItemCount(): Int {
        return myList.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

        var img = itemView.findViewById(R.id.img_services) as ImageView
        var title = itemView.findViewById(R.id.service_title_item) as TextView
        var description = itemView.findViewById(R.id.service_description_item) as TextView


    }

}