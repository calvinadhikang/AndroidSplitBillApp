package com.example.splitbill

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Bill(
    var id: Int = ctr,
    var owner: User,
    var keterangan: String,
    var promo: Int,
    var ongkos: Int,
    var biaya: Int,
    var buy: ArrayList<Buy> = arrayListOf()
) : Parcelable {

    companion object{
        var ctr = 1
    }
}