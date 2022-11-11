package com.example.splitbill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AddBillFragment(
    var user: User,
    var arrContact: ArrayList<User>,
    var arrUser: ArrayList<User>,
    var cb: (new: Bill)->Unit
) : Fragment() {

    lateinit var tvTotal: TextView
    lateinit var edtOngkos: EditText
    lateinit var edtKeterangan: EditText
    lateinit var edtPromo: EditText
    lateinit var rvTemp: RecyclerView

    lateinit var adapterBill: BillAdapter

    var arrTemp: ArrayList<Buy> = arrayListOf()
    var res = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvHello: TextView = view.findViewById(R.id.tvAddHello)
        tvHello.text = user.Name

        edtKeterangan = view.findViewById(R.id.edtKeterangan)
        edtOngkos = view.findViewById(R.id.edtTotalOngkos)
        edtPromo = view.findViewById(R.id.edtTotalPromo)
        rvTemp = view.findViewById(R.id.rvTemp)

        adapterBill = BillAdapter(arrTemp){position ->
            arrTemp.removeAt(position)
            adapterBill.notifyDataSetChanged()
            count()
        }
        rvTemp.adapter = adapterBill
        rvTemp.layoutManager = LinearLayoutManager(view.context)

        val btnAdd: Button = view.findViewById(R.id.btnAddBill)
        val btnSend: Button = view.findViewById(R.id.btnSend)

        tvTotal = view.findViewById(R.id.tvBillTotal)

        edtOngkos.doOnTextChanged { text, start, before, count ->
            count()
        }
        edtPromo.doOnTextChanged { text, start, before, count ->
            count()
        }

        btnAdd.setOnClickListener {
            val frag = AddSplitFragment(arrContact, arrUser, user){ new->
                arrTemp.add(new)
                count()
            }
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.frame1, frag)
                .addToBackStack(null)
                .commit()
        }

        btnSend.setOnClickListener {
            val new = Bill(Bill.ctr,
                user,
                edtKeterangan.text.toString(),
                edtPromo.text.toString().toInt(),
                edtOngkos.text.toString().toInt(),
                res,
                arrTemp
            )
            cb(new)
            Bill.ctr++
            Toast.makeText(view.context, "Berhasil tambah Bill", Toast.LENGTH_SHORT).show()
        }
    }

    fun count(){
        res = 0

        var ongkos = 0
        var inongkos = edtOngkos.text.toString()
        var promo = 0
        var inpromo = edtPromo.text.toString()

        if (inongkos != ""){
            ongkos = inongkos.toInt()
        }

        if (inpromo != ""){
            promo = inpromo.toInt()
        }

        if (arrTemp.count() == 0){
            res = ongkos - promo
        }else{
            var countOngkos = ongkos / arrTemp.count()
            var countPromo = promo / arrTemp.count()

            arrTemp.forEachIndexed { index, buy ->
                buy.HargaAdd = buy.HargaAsli + countOngkos - countPromo
            }

            arrTemp.forEachIndexed { index, buy ->
                res += buy.HargaAdd
            }
        }

        tvTotal.text = "TOTAL BIAYA : RP ${res}"
    }
}