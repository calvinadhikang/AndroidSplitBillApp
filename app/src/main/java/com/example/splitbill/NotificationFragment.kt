package com.example.splitbill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class NotificationFragment(
    var user: User,
    var adpt: NotifAdapter
) : Fragment() {

    lateinit var tvHello:TextView
    lateinit var rv:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHello = view.findViewById(R.id.tvNotifHello)
        rv = view.findViewById(R.id.rvNotification)
        rv.adapter = adpt
        rv.layoutManager = LinearLayoutManager(view.context)

        tvHello.text = "Hi, ${user.Name} !"
    }
}