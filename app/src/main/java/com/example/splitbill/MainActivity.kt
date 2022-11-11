package com.example.splitbill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var btnLogin :Button
    lateinit var btnRegister :Button
    lateinit var edtPassword :EditText
    lateinit var edtUsername :EditText

    var arrUser: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //isi login awal
        var init = User("Dikang", "ca", "ca")
        init.Contacts.add(User("Kontak 1", "k1", "k1"))
        init.Contacts.add(User("Kontak 2", "k2", "k2"))
        arrUser.add(init)

        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnToRegister)
        edtUsername = findViewById(R.id.txtUsername)
        edtPassword = findViewById(R.id.txtPassword)

        btnRegister.setOnClickListener {
            launcher.launch(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            var usr = edtUsername.text.toString()
            var pas = edtPassword.text.toString()

            if (usr == "admin" && pas == "nimda"){

            }else{

                arrUser.forEachIndexed { index, user ->
                    if (user.Username == usr && user.Password == pas){
                        var intent = Intent(this, UserActivity::class.java)
                        intent.putExtra("IDX", index)
                        intent.putExtra("ARRUSER", arrUser)
                        launcher.launch(intent)
                    }
                }

            }
        }
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->

        if (result.resultCode == RESULT_OK){
            val data = result.data
            if (data!=null){
                var name = data.getStringExtra("NAME")!!
                var user = data.getStringExtra("USER")!!
                var pass = data.getStringExtra("PASS")!!

                arrUser.add(User(name, user, pass))
            }
        }
        else if (result.resultCode == 100){
            val data = result.data
            if (data!=null){
                var arr = data.getParcelableArrayListExtra<User>("ARRUSER")!!

                arrUser = arr
            }
        }
    }
}