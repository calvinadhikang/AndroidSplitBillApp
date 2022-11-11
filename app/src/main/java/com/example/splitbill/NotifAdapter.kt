package com.example.splitbill

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotifAdapter(
    var contacts: ArrayList<Notif>,
): RecyclerView.Adapter<NotifAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.notif_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val obj = contacts[position]

        holder.tvOwner.text = "Owner :" + obj.owner
        holder.tvKet.text = obj.keterangan
        holder.tvBiaya.text = "Biaya Rp: " + obj.biaya.toString()
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class  CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvOwner: TextView = view.findViewById(R.id.tvNotifOwner)
        val tvKet: TextView = view.findViewById(R.id.tvNotifKeterangan)
        val tvBiaya: TextView = view.findViewById(R.id.tvNotifBiaya)
    }
}