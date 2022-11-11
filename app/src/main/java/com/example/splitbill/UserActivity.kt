package com.example.splitbill

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {

    lateinit var frmTop: FrameLayout
    lateinit var frmBtm: FrameLayout
    lateinit var nav: BottomNavigationView

    lateinit var fragmentContacts: ContactsFragment
    lateinit var fragmentHistory: HistoryFragment
    lateinit var fragmentAddBill: AddBillFragment
    lateinit var fragmentNotif: NotificationFragment

    lateinit var adapterContact: ContactsRvAdapter
    lateinit var adapterHistory: HistoryAdapter
    lateinit var adapterBill: BillAdapter
    lateinit var adapterNotif: NotifAdapter

    lateinit var user:User
    var idx:Int = -1
    lateinit var arrUser: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        idx = intent.getIntExtra("IDX", -1)
        arrUser = intent.getParcelableArrayListExtra<User>("ARRUSER")!!
        user = arrUser.get(idx)

        Toast.makeText(this, user.Contacts.size.toString(), Toast.LENGTH_SHORT).show()

        frmTop = findViewById(R.id.frame1)
//        frmBtm = findViewById(R.id.frame2)
        nav = findViewById(R.id.navbar)

        adapterContact = ContactsRvAdapter(user.Contacts){ username ->
            var idx = -1
            user.Contacts.forEachIndexed { index, user ->
                if (user.Username == username){
                    idx = index
                }
            }

            user.Contacts.removeAt(idx)
            adapterContact.notifyDataSetChanged()
        }
        adapterHistory = HistoryAdapter(user.Bills)
        adapterNotif = NotifAdapter(user.Notifications)

        nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_contact->{
                    showContactFragment()
                }
                R.id.menu_add-> {
//                    Toast.makeText(this, "Kepilih", Toast.LENGTH_SHORT).show()
                    showBillFragment()
                }
                R.id.menu_history->{
                    showHistoryFragment()
                }
                R.id.menu_notification->{
                    showNotifFragment()
                }
            }
            true
        }

        showContactFragment()
    }

    fun showContactFragment(){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentContacts = ContactsFragment(user, adapterContact, arrUser){new: User ->
            user.Contacts.add(new)
            Toast.makeText(this, "Berhasil add user ${new.Username}", Toast.LENGTH_SHORT).show()
            adapterContact.notifyDataSetChanged()
        }

        fragmentManager.replace(R.id.frame1, fragmentContacts)
        fragmentManager.commit()
    }

    fun showHistoryFragment(){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentHistory = HistoryFragment(user, adapterHistory, arrUser)

        fragmentManager.replace(R.id.frame1, fragmentHistory)
        fragmentManager.commit()
    }

    fun showBillFragment(){
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentAddBill = AddBillFragment(user, user.Contacts, arrUser){new: Bill ->
            user.Bills.add(new)

            new.buy.forEachIndexed { index, buy ->
                var targetKey = buy.idxUser

                var getUser = arrUser[targetKey]
                if (getUser.Username == this.user.Username){
                    this.user.Notifications.add(Notif(new.keterangan, new.owner.Name, buy.HargaAdd))
                    adapterNotif.notifyDataSetChanged()
                }else{
                    getUser.Notifications.add(Notif(new.keterangan, new.owner.Name, buy.HargaAdd))
                }
            }
        }

        fragmentManager.replace(R.id.frame1, fragmentAddBill)
        fragmentManager.commit()
    }

    fun showNotifFragment(){
        Toast.makeText(this, "Jumlah Notif : ${user.Notifications.size}", Toast.LENGTH_SHORT).show()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentNotif = NotificationFragment(user, adapterNotif)

        fragmentManager.replace(R.id.frame1, fragmentNotif)
        fragmentManager.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_logout -> {
                val intent = Intent()
                intent.putExtra("ARRUSER", arrUser)
                setResult(100, intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}