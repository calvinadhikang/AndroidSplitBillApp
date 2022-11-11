package com.example.splitbill

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.RecursiveAction

class ContactsRvAdapter(
    var contacts: ArrayList<User>,
    var cb: (username: String)->Unit
): RecyclerView.Adapter<ContactsRvAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.contact_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val obj = contacts[position]

        holder.tvName.text = obj.Name

        holder.btn.setBackgroundColor(Color.RED)
        holder.btn.setOnClickListener {
            cb(obj.Username)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class  CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName: TextView = view.findViewById(R.id.tvContact)
        val btn: Button = view.findViewById(R.id.btnContact)
    }
}