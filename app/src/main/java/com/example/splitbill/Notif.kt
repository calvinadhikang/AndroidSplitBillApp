package com.example.splitbill

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Notif(
    var keterangan:String,
    var owner:String,
    var biaya: Int
):Parcelable {
}