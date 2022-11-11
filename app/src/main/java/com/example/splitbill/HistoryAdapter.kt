package com.example.splitbill

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(
    var contacts: ArrayList<Bill>
): RecyclerView.Adapter<HistoryAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.history_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val obj = contacts[position]

        holder.tvJudul.text = obj.keterangan
        holder.tvBiaya.text = "Total Biaya Rp. ${obj.biaya}"
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class  CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvJudul: TextView = view.findViewById(R.id.tvHistoryJudul)
        val tvBiaya: TextView = view.findViewById(R.id.tvHistoryBiaya)
    }
}