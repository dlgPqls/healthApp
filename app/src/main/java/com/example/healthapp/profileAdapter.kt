package com.example.healthapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class profileAdapter(val profileList: ArrayList<profiles>) : RecyclerView.Adapter<profileAdapter.CustomViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClilck(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profileAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return CustomViewHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: profileAdapter.CustomViewHolder, position: Int) {
        holder.name.text = profileList.get(position).name
        holder.water.text = ((profileList.get(position).water/profileList.get(position).objWater)*100).toString()+"%"
        holder.work.text = ((profileList.get(position).work/profileList.get(position).objWork)*100).toString()+"%"

    }

    class CustomViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val water = itemView.findViewById<TextView>(R.id.tv_water)
        val work = itemView.findViewById<TextView>(R.id.tv_work)

        init{
            itemView.setOnClickListener {
                listener.onItemClilck(adapterPosition)
            }
        }

    }
}