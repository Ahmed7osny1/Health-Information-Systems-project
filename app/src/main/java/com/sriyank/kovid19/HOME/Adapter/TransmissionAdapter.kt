package com.sriyank.kovid19.HOME.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.kovid19.HOME.services.ServicesData
import com.sriyank.kovid19.R

class TransmissionAdapter(var myList: ArrayList<ServicesData>) :
    RecyclerView.Adapter<TransmissionAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var v = LayoutInflater.from(parent.context).inflate(R.layout.transmission_items,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var spread = myList[position]

        holder.img.setImageResource(spread.Image)
        holder.title.text = spread.Title
        holder.discription.text = spread.Description

    }

    override fun getItemCount(): Int {
        return myList.size
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        var img = itemView.findViewById<ImageView>(R.id.spread_img)
        var title = itemView.findViewById<TextView>(R.id.spread_title)
        var discription = itemView.findViewById<TextView>(R.id.spread_discription)
    }


}