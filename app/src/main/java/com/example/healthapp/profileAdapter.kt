package com.example.healthapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class profileAdapter(val profileList: ArrayList<profiles>) : RecyclerView.Adapter<profileAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: profileAdapter.CustomViewHolder, position: Int) {
        holder.name.text = profileList.get(position).name
        holder.water.text = profileList.get(position).water.toString()+"%"
        holder.work.text = profileList.get(position).work.toString()+"%"
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val water = itemView.findViewById<TextView>(R.id.tv_water)
        val work = itemView.findViewById<TextView>(R.id.tv_work)
    }

}