package com.example.splitbill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment(
    var user: User,
    var adpt: HistoryAdapter,
    var arrUser: ArrayList<User>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rvHistory)
        rv.adapter = adpt
        rv.layoutManager = LinearLayoutManager(view.context)

        val tvHello: TextView = view.findViewById(R.id.tvHistoryHello)
        tvHello.text = "Hello ${user.Name}"
    }
}