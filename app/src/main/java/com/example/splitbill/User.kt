package com.example.splitbill

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    var Name: String,
    var Username: String,
    var Password: String,
    var Contacts: ArrayList<User> = arrayListOf(),
    var Notifications: ArrayList<Notif> = arrayListOf(),
    var Bills: ArrayList<Bill> = arrayListOf()
): Parcelable {
}