package com.example.splitbill

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BillAdapter(
    var contacts: ArrayList<Buy>,
    var cb: (position: Int)->Unit
): RecyclerView.Adapter<BillAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.bill_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val obj = contacts[position]

        holder.tvKeterangan.text = obj.keterangan
        holder.tvTotal.text = "Rp. ${obj.HargaAdd}"
        holder.tvBy.text = "By: ${obj.user}"

        holder.itemView.setOnLongClickListener {
            cb(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class  CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvKeterangan: TextView = view.findViewById(R.id.tvBillKeterangan)
        val tvTotal: TextView = view.findViewById(R.id.tvBillUserTotal)
        val tvBy: TextView = view.findViewById(R.id.tvBillBy)
    }
}