package com.example.splitbill

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult

class RegisterActivity : AppCompatActivity() {

    lateinit var btnLogin: Button
    lateinit var btnRegis: Button
    lateinit var eName: EditText
    lateinit var eUser: EditText
    lateinit var ePass: EditText
    lateinit var eCpas: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegis = findViewById(R.id.btnRegister)
        btnLogin = findViewById(R.id.btnToLogin)
        eName = findViewById(R.id.regName)
        eUser = findViewById(R.id.regUser)
        ePass = findViewById(R.id.regPass)
        eCpas = findViewById(R.id.regCPass)

        btnLogin.setOnClickListener {
            finish()
        }

        btnRegis.setOnClickListener {
            var name = eName.text.toString()
            var user = eUser.text.toString()
            var pass = ePass.text.toString()
            var cpas = eCpas.text.toString()

            if (name == "" || user == "" || pass == "" || cpas == ""){
                Toast.makeText(this, "Inputan tidak boleh kosong" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (cpas != pass){
                Toast.makeText(this, "Password dan Confirm harus sama" , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val new = User(name, user, pass)

            val hasil = Intent()
            hasil.putExtra("NAME", name)
            hasil.putExtra("USER", user)
            hasil.putExtra("PASS", pass)
            setResult(Activity.RESULT_OK, hasil)
            finish()
        }
    }
}