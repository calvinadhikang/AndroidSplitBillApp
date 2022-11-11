package com.example.splitbill

import android.icu.text.MessagePattern
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Buy(
    var id: Int = 1,
    var user: String,
    var keterangan: String,
    var HargaAsli: Int,
    var HargaAdd: Int = 0,
    var idxUser: Int = -1
): Parcelable {

    companion object{
        var ctr: Int = 1
    }
}