package com.example.splitbill

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class AddSplitFragment(
    var arr: ArrayList<User>,
    var arrUser: ArrayList<User>,
    var self: User,
    var cb: (new: Buy)->Unit
) : Fragment() {

    lateinit var btnBack: Button
    lateinit var btnAdd: Button
    lateinit var spinner: Spinner
    lateinit var edtKet: EditText
    lateinit var edtHarga: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_split, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner = view.findViewById(R.id.spinner)
        btnAdd = view.findViewById(R.id.btnAddSplit)
        btnBack = view.findViewById(R.id.btnBackSplit)
        edtKet = view.findViewById(R.id.editTextTextPersonName2)
        edtHarga = view.findViewById(R.id.editTextNumber)

        var arrNama:ArrayList<String> = arrayListOf()
        arrNama.add(self.Name)
        arr.forEachIndexed { index, user ->
            arrNama.add(user.Name)
        }
        var spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item,arrNama)
        spinner.adapter = spinnerAdapter

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnAdd.setOnClickListener{
            var idx = -1
            arrUser.forEachIndexed { index, s ->
                if (s.Name == spinner.selectedItem.toString()){
                    idx = index
                }
            }

            Log.i("Info Idx User", idx.toString())

            val new = Buy(Buy.ctr, spinner.selectedItem.toString(), edtKet.text.toString(), edtHarga.text.toString().toInt(), idx)
            new.idxUser = idx
            Buy.ctr++

            cb(new)
            parentFragmentManager.popBackStack()
        }
    }
}