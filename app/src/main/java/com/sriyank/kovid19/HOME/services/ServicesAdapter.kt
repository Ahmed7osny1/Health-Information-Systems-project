package com.sriyank.kovid19.HOME.services

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.kovid19.R

class servicesAdapter (var myList : ArrayList <ServicesData>) :
    RecyclerView.Adapter<servicesAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {

        fun onItemClicked(position: Int)

     }

    fun setonItemClickListener(listener : onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.services_items,parent,false)

        return ViewHolder(v,mListener)
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

    class ViewHolder (itemView : View,listener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        var img = itemView.findViewById(R.id.img_services) as ImageView
        var title = itemView.findViewById(R.id.service_title_item) as TextView
        var description = itemView.findViewById(R.id.service_description_item) as TextView

        init {

            itemView.setOnClickListener {

                listener.onItemClicked(adapterPosition)
            }

        }
    }
}