package com.example.splitbill

import android.app.UiAutomation
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager


class AddContactsFragment(
    var arrUser: ArrayList<User>,
    var cb: (usr: User)->Unit
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edt: EditText = view.findViewById(R.id.editTextTextPersonName)
        val btnAdd: Button = view.findViewById(R.id.btnAdd)
        val btnBack: Button = view.findViewById(R.id.btnBack)
        btnBack.setBackgroundColor(Color.RED)

        btnAdd.setOnClickListener {
            var target = edt.text.toString()

            var found: User? = null
            arrUser.forEachIndexed { index, user ->
                if (user.Username == target){
                    found = user
                }
            }

            if (found != null){
                cb(found!!)
                parentFragmentManager.popBackStack()
            }else{
                Toast.makeText(view.context, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
//            Toast.makeText(view.context, "Back", Toast.LENGTH_SHORT).show()
//            var fm: FragmentManager? = activity?.supportFragmentManager
//            fm?.popBackStack()
            parentFragmentManager.popBackStack()
        }
    }
}