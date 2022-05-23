package com.sriyank.kovid19.HOME

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.kovid19.HOME.services.ServicesData
import com.sriyank.kovid19.R

class AdviceAdapter (var myList : ArrayList<ServicesData>) : RecyclerView.Adapter<AdviceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.advice_items,parent,false)

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

        var img = itemView.findViewById(R.id.advice_img) as ImageView
        var title = itemView.findViewById(R.id.txt_title) as TextView
        var description = itemView.findViewById(R.id.txt_description) as TextView


    }

}