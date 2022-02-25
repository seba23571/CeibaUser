package com.supraweb.ceibauser.data.db.entities.usersresponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
) : Parcelable {

}