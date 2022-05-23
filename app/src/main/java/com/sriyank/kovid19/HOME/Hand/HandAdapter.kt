package com.sriyank.kovid19.HOME.Hand

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sriyank.kovid19.R

class HandAdapter(var myList : ArrayList<HandData>) : RecyclerView.Adapter<HandAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var v = LayoutInflater.from(parent.context).inflate(R.layout.wash_items,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var HandWash = myList[position]

        holder.img.setImageResource(HandWash.img)
        holder.txt.text = HandWash.text

    }

    override fun getItemCount(): Int {
        return myList.size
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var img = itemView.findViewById(R.id.wash_img) as ImageView
        var txt = itemView.findViewById(R.id.wash_txt) as TextView

    }

}