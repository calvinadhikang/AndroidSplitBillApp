package com.example.splitbill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactsFragment(
    var user: User,
    var contactsAdapter: ContactsRvAdapter,
    var arrUser: ArrayList<User>,
    var addKontak: (usr: User)->Unit
) : Fragment() {

    lateinit var rvContact: RecyclerView
    lateinit var btnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvContact = view.findViewById(R.id.rvContacts)
        rvContact.adapter = contactsAdapter
        rvContact.layoutManager = LinearLayoutManager(view.context)

        val tvHello: TextView = view.findViewById(R.id.tvHello)
        tvHello.text = "Hi, ${user.Name}"

        btnAdd = view.findViewById(R.id.btnAddContact)
        btnAdd.setOnClickListener {
            val frag = AddContactsFragment(arrUser){ usr->
                addKontak(usr)
            }
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.frame1, frag)
                .addToBackStack(null)
                .commit()
        }
    }
}